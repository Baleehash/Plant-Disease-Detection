import os
import numpy as np
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from sklearn.metrics import classification_report, confusion_matrix

# Path dataset dan model
base_dir = r'C:\Users\Lenovo\Latihan_Hari_2\project\New Plant Diseases Dataset(Augmented)'
test_dir = os.path.join(base_dir, 'test', 'test')
model_path = os.path.join('models', 'plant_disease_model.h5')

# Memuat model
if not os.path.exists(model_path):
    raise FileNotFoundError(f"Model tidak ditemukan di: {model_path}")
model = load_model(model_path)
print("Model berhasil dimuat.")

# Menyiapkan ImageDataGenerator untuk dataset uji
test_datagen = ImageDataGenerator(rescale=1.0/255.0)

# Membuat generator untuk dataset uji
test_generator = test_datagen.flow_from_directory(
    test_dir,
    target_size=(128, 128), 
    batch_size=32,
    class_mode='categorical',
    shuffle=False  # Agar urutan gambar tetap untuk evaluasi
)

# Mengecek jumlah kelas pada dataset uji
print(f"\nJumlah kelas di dataset uji: {test_generator.num_classes}")
print(f"Nama kelas pada dataset uji: {test_generator.class_indices}")

# Mengevaluasi model
loss, accuracy = model.evaluate(test_generator)
print(f"\nHasil evaluasi:")
print(f"Loss: {loss:.4f}")
print(f"Akurasi: {accuracy:.4f}")


# Membuat prediksi pada data uji
y_pred = model.predict(test_generator)
y_pred_classes = np.argmax(y_pred, axis=1)
y_true_classes = test_generator.classes

# Menampilkan classification report
labels = list(test_generator.class_indices.keys())  # Nama kelas
print("\nClassification Report:")
print(classification_report(y_true_classes, y_pred_classes, target_names=labels))

# Menampilkan confusion matrix
print("\nConfusion Matrix:")
conf_matrix = confusion_matrix(y_true_classes, y_pred_classes)
print(conf_matrix)
