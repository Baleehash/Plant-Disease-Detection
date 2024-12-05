const startCameraButton = document.getElementById("startCamera");
const captureButton = document.getElementById("captureButton");
const video = document.getElementById("video");
const canvas = document.getElementById("canvas");
const submitCapture = document.getElementById("submitCapture");
const hiddenFileInput = document.getElementById("hiddenFileInput");

let stream;

// Event listener untuk tombol Start Camera
startCameraButton.addEventListener("click", async () => {
    try {
        // Minta akses kamera
        stream = await navigator.mediaDevices.getUserMedia({ video: true });
        video.srcObject = stream; // Tampilkan video dari kamera
        video.style.display = "block"; // Tampilkan elemen video
        captureButton.style.display = "inline-block"; // Tampilkan tombol Capture
    } catch (error) {
        alert("Unable to access camera: " + error.message); // Error jika akses kamera ditolak
    }
});

// Event listener untuk tombol Capture
captureButton.addEventListener("click", () => {
    const context = canvas.getContext("2d");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;

    // Gambar frame dari video ke canvas
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    // Convert canvas ke Blob
    canvas.toBlob((blob) => {
        const file = new File([blob], "capture.jpg", { type: "image/jpeg" });
        const dataTransfer = new DataTransfer();
        dataTransfer.items.add(file);
        hiddenFileInput.files = dataTransfer.files;

        // Submit form
        submitCapture.click();
    });

    // Matikan kamera setelah capture
    stream.getTracks().forEach(track => track.stop());
    video.style.display = "none";
    captureButton.style.display = "none";
});
