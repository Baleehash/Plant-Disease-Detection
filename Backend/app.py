from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from routes.labels import router as labels_router
from routes.predict import router as predict_router  # Import router predict
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
app.include_router(predict_router, prefix="/predict")
logging.info("Predict router included successfully")

# Jalankan server
if __name__ == "__main__":
    import uvicorn
    try:
        logging.info("Starting FastAPI app...")
        uvicorn.run("app:app", host="127.0.0.1", port=8000, reload=True)
    except Exception as e:
        logging.error(f"An error occurred: {e}")


@app.get("/")
def read_root():
    return {"message": "Selamat datang di Plant Disease Detection API!"}
