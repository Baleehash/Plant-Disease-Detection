import os
import json
from fastapi import APIRouter

router = APIRouter()

# Path ke file label
LABEL_PATH = "assets/class_labels.json"

# Memuat label
def load_labels(json_path):
    with open(json_path, 'r') as f:
        data = json.load(f)
    return data['labels']

if not os.path.exists(LABEL_PATH):
    raise FileNotFoundError(f"File label tidak ditemukan di: {LABEL_PATH}")
labels = load_labels(LABEL_PATH)

# Endpoint untuk melihat daftar label
@router.get("/labels")
def get_labels():
    return {"total_classes": len(labels), "labels": labels}
