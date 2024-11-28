from fastapi import APIRouter
from .predict import router as predict_router
from .labels import router as labels_router

router = APIRouter()
router.include_router(predict_router, prefix="/predict", tags=["Predict"])
router.include_router(labels_router, tags=["Labels"])
