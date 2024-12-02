import os
import shutil
import numpy as np
from fastapi import APIRouter, UploadFile, File, HTTPException
from fastapi.responses import JSONResponse
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import load_img, img_to_array
from datetime import datetime
import mysql.connector
import json

router = APIRouter()

# Path ke model dan file label
MODEL_PATH = "models/plant_disease_model.h5"
LABEL_PATH = "class_labels.json"
UPLOAD_FOLDER = "uploads"

# Konfigurasi database
DB_CONFIG = {
    "host": "localhost",
    "user": "root",
    "password": "password",
    "database": "plant_disease_app",
}

# Memuat model
if not os.path.exists(MODEL_PATH):
    raise FileNotFoundError(f"Model tidak ditemukan di: {MODEL_PATH}")
model = load_model(MODEL_PATH)

# Memuat label
def load_labels(json_path):
    with open(json_path, 'r') as f:
        data = json.load(f)
    return data['labels']

if not os.path.exists(LABEL_PATH):
    raise FileNotFoundError(f"File label tidak ditemukan di: {LABEL_PATH}")
labels = load_labels(LABEL_PATH)

# Membuat folder uploads jika belum ada
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# Fungsi untuk menyimpan prediksi ke database
def save_prediction(user_id, image_filename, label, confidence):
    try:
        connection = mysql.connector.connect(**DB_CONFIG)
        cursor = connection.cursor()
        query = """
            INSERT INTO predictions (user_id, image_filename, label, confidence)
            VALUES (%s, %s, %s, %s)
        """
        cursor.execute(query, (user_id, image_filename, label, confidence))
        connection.commit()
    except mysql.connector.Error as e:
        raise HTTPException(status_code=500, detail=f"Database error: {e}")
    finally:
        cursor.close()
        connection.close()

# Fungsi untuk memproses gambar dan melakukan prediksi
async def process_image(file: UploadFile, user_id: int):
    try:
        # Tambahkan timestamp pada nama file untuk menghindari konflik nama
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        filename = f"{timestamp}_{file.filename}"
        file_location = os.path.join(UPLOAD_FOLDER, filename)

        # Menyimpan file ke folder uploads
        with open(file_location, "wb") as buffer:
            shutil.copyfileobj(file.file, buffer)

        # Memuat gambar
        img = load_img(file_location, target_size=(128, 128))
        img_array = img_to_array(img) / 255.0
        img_array = np.expand_dims(img_array, axis=0)

        # Melakukan prediksi
        predictions = model.predict(img_array)
        predicted_class = np.argmax(predictions[0])
        confidence = predictions[0][predicted_class]
        label = labels[predicted_class]

        # Simpan prediksi ke database
        save_prediction(user_id, filename, label, float(confidence))

        return {
            "filename": filename,
            "label": label,
            "confidence": float(confidence),
        }

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)

# Endpoint untuk meng-upload gambar
@router.post("/predict/upload")
async def predict_image(file: UploadFile = File(...), user_id: int = 1):
    return await process_image(file, user_id)

# Endpoint untuk menangani gambar yang di-capture
@router.post("/predict/capture")
async def predict_from_capture(file: UploadFile = File(...), user_id: int = 1):
    return await process_image(file, user_id)
