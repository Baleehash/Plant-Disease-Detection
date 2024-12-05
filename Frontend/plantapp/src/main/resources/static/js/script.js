const startCamera = document.getElementById("startCamera");
const stopCamera = document.getElementById("stopCamera");
const captureButton = document.getElementById("captureButton");
const video = document.getElementById("video");

let stream;

// Start Camera
startCamera.addEventListener("click", async () => {
    try {
        stream = await navigator.mediaDevices.getUserMedia({ video: true });
        video.srcObject = stream;
        video.style.display = "block";
        startCamera.style.display = "none";
        stopCamera.style.display = "inline-block";
        captureButton.style.display = "inline-block";
    } catch (error) {
        alert("Camera access denied!");
    }
});

// Stop Camera
stopCamera.addEventListener("click", () => {
    stream.getTracks().forEach((track) => track.stop());
    video.style.display = "none";
    startCamera.style.display = "inline-block";
    stopCamera.style.display = "none";
    captureButton.style.display = "none";
});

// Capture Image
captureButton.addEventListener("click", () => {
    const canvas = document.getElementById("canvas");
    const context = canvas.getContext("2d");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video, 0, 0, canvas.width, canvas.height);
    canvas.style.display = "block";
});