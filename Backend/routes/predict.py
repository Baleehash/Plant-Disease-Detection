import os
import shutil
import numpy as np
from fastapi import APIRouter, UploadFile, File
from fastapi.responses import JSONResponse
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import load_img, img_to_array
import json
from datetime import datetime

router = APIRouter()

MODEL_PATH = "models/plant_disease_model.h5"
LABEL_PATH = "class_labels.json"
UPLOAD_FOLDER = "uploads"

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

# Fungsi untuk memproses gambar dan melakukan prediksi
async def process_image(file: UploadFile):
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

        return {
            "filename": filename,
            "label": labels[predicted_class],
            "confidence": float(confidence),
        }

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)

# Endpoint untuk meng-upload gambar
@router.post("/predict/upload")
async def predict_image(file: UploadFile = File(...)):
    return await process_image(file)

# Endpoint untuk menangani gambar yang di-capture
@router.post("/predict/capture")
async def predict_from_capture(file: UploadFile = File(...)):
    return await process_image(file)
