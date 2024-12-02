from fastapi import FastAPI, Depends, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from routes.labels import router as labels_router
from routes.predict import router as predict_router  # Import router predict
import mysql.connector
from mysql.connector import Error
import logging

app = FastAPI()

# Middleware CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Tambahkan router
app.include_router(labels_router)
app.include_router(predict_router)

logging.basicConfig(level=logging.INFO)
logging.info("Predict router included successfully")

# Konfigurasi MySQL
db_config = {
    "host": "localhost",
    "user": "root",
    "password": "password",
    "database": "plant_disease_app"
}

# Fungsi untuk mendapatkan koneksi database
def get_db_connection():
    try:
        connection = mysql.connector.connect(**db_config)
        if connection.is_connected():
            logging.info("Database connected successfully")
            return connection
    except Error as e:
        logging.error(f"Database connection error: {e}")
        raise HTTPException(status_code=500, detail="Failed to connect to the database")

# Endpoint untuk memeriksa koneksi database
@app.get("/db-check")
def db_check():
    connection = get_db_connection()
    if connection:
        connection.close()
        return {"message": "Database connection is successful"}
    else:
        raise HTTPException(status_code=500, detail="Database connection failed")

# Endpoint utama
@app.get("/")
def read_root():
    return {"message": "Selamat datang di Plant Disease Detection API!"}

# Jalankan server
if __name__ == "__main__":
    import uvicorn
    try:
        logging.info("Starting FastAPI app...")
        uvicorn.run("app:app", host="127.0.0.1", port=8000, reload=True)
    except Exception as e:
        logging.error(f"An error occurred: {e}")
