const startCameraButton = document.getElementById("startCamera");
const stopCameraButton = document.getElementById("stopCamera");
const captureButton = document.getElementById("captureButton");
const video = document.getElementById("video");
const canvas = document.getElementById("canvas");
const submitCapture = document.getElementById("submitCapture");
const hiddenFileInput = document.getElementById("hiddenFileInput");

let stream;

// Start Camera
startCameraButton.addEventListener("click", async () => {
    try {
        stream = await navigator.mediaDevices.getUserMedia({ video: true });
        video.srcObject = stream;
        video.style.display = "block";
        startCameraButton.style.display = "none"; // Sembunyikan tombol Start Camera
        stopCameraButton.style.display = "inline-block"; // Tampilkan tombol Stop Camera
        captureButton.style.display = "inline-block";
    } catch (error) {
        alert("Unable to access camera: " + error.message);
    }
});

// Stop Camera
stopCameraButton.addEventListener("click", () => {
    if (stream) {
        stream.getTracks().forEach(track => track.stop()); // Hentikan semua stream
    }
    video.style.display = "none";
    stopCameraButton.style.display = "none";
    startCameraButton.style.display = "inline-block"; // Tampilkan kembali tombol Start Camera
    captureButton.style.display = "none";
});

// Capture Image
captureButton.addEventListener("click", () => {
    const context = canvas.getContext("2d");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    canvas.toBlob((blob) => {
        const file = new File([blob], "capture.jpg", { type: "image/jpeg" });
        const dataTransfer = new DataTransfer();
        dataTransfer.items.add(file);
        hiddenFileInput.files = dataTransfer.files;

        // Submit form
        submitCapture.click();
    });

    // Optional: Matikan kamera setelah capture
    if (stream) {
        stream.getTracks().forEach(track => track.stop());
    }
    video.style.display = "none";
    stopCameraButton.style.display = "none";
    startCameraButton.style.display = "inline-block";
    captureButton.style.display = "none";
});

function toggleDescription(cardElement) {
    // Dapatkan elemen card yang di-klik
    const card = cardElement.closest('.card');
    const description = card.querySelector('.description-section');

    // Cek apakah deskripsi terlihat
    const isVisible = description.style.display === 'block';

    // Sembunyikan semua deskripsi di semua card
    document.querySelectorAll('.card .description-section').forEach((desc) => {
        desc.style.display = 'none';
    });

    // Tampilkan deskripsi hanya pada card yang di-klik
    if (!isVisible) {
        description.style.display = 'block';
    }
}






