package com.example.plantapp.controller;

import com.example.plantapp.model.PredictionResult;
import com.example.plantapp.service.PredictionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PredictionWebController {

    private final PredictionService predictionService;

    public PredictionWebController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        // Simpan gambar yang diunggah
        String uploadedFileName = "uploaded_" + file.getOriginalFilename();
        Path uploadPath = Paths.get("plantapp/src/main/resources/static/saved-pictures/" + uploadedFileName);
        Files.write(uploadPath, file.getBytes());

        // Prediksi
        PredictionResult result = predictionService.uploadImage(file);

        // Tambahkan data untuk ditampilkan di result-upload.html
        redirectAttributes.addFlashAttribute("label", result.getLabel());
        redirectAttributes.addFlashAttribute("confidence", result.getConfidence());
        redirectAttributes.addFlashAttribute("description", getDescriptionForLabel(result.getLabel()));

        return "redirect:/result-upload";
    }
    @GetMapping("/result-upload")
    public String showResultForUpload() {
        return "result-upload"; // Tampilkan result-upload.html
    }
    @PostMapping("/capture")
    public String captureImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        // Simpan gambar yang ditangkap
        String capturedFileName = "captured_" + file.getOriginalFilename();
        Path capturePath = Paths.get("plantapp/src/main/resources/static/saved-pictures/" + capturedFileName);
        Files.write(capturePath, file.getBytes());

        // Prediksi
        PredictionResult result = predictionService.captureImage(file);

        // Tambahkan data untuk ditampilkan di result-upload.html
        redirectAttributes.addFlashAttribute("label", result.getLabel());
        redirectAttributes.addFlashAttribute("confidence", result.getConfidence());
        redirectAttributes.addFlashAttribute("description", getDescriptionForLabel(result.getLabel()));

        return "redirect:/result-capture";
    }
    @GetMapping("/result-capture")
    public String showResultForCapture() {
        return "result-capture"; // Tampilkan result-capture.html
    }

    // Metode untuk mendapatkan deskripsi berdasarkan label
    private String getDescriptionForLabel(String label) {
        Map<String, String> descriptions = new HashMap<>();
        descriptions.put("Healthy", "The plant appears to be healthy without any visible diseases.");
        descriptions.put("Diseased", "The plant shows symptoms of disease. Consider applying appropriate treatment.");
        // Tambahkan deskripsi lainnya sesuai label yang ada
        return descriptions.getOrDefault(label, "No additional information available for this label.");
    }
}
