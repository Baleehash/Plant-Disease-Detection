package com.example.plantapp.controller;

import com.example.plantapp.model.PredictionResult;
import com.example.plantapp.service.DiseaseDescriptionService;
import com.example.plantapp.service.PredictionService;
import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.ui.Model;


@Controller
public class PredictionWebController {

    private final PredictionService predictionService;
    private final DiseaseDescriptionService descriptionService;

    public PredictionWebController(PredictionService predictionService, DiseaseDescriptionService descriptionService) {
        this.predictionService = predictionService;
        this.descriptionService = descriptionService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/disease-library")
    public String diseaseLibrary() {
        return "disease-library";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        // Lakukan prediksi terlebih dahulu untuk mendapatkan label
        PredictionResult result = predictionService.uploadImage(file);
        String formatLabel = formatLabel(result.getLabel()); // Label hasil prediksi

        // Bentuk nama file berdasarkan label hasil prediksi
        String uploadedFileName = "uploaded_" + formatLabel.replaceAll("[^a-zA-Z0-9_-]", "_") + ".jpg"; // Hanya karakter aman
        Path uploadPath = Paths.get("plantapp/src/main/resources/static/saved-pictures/uploaded/" + uploadedFileName);

        // Simpan file yang diupload
        Files.write(uploadPath, file.getBytes());

        // Ambil deskripsi dan perawatan berdasarkan label
        String description = descriptionService.getDescription(formatLabel);
        String treatment = descriptionService.getTreatment(formatLabel);

        // Tambahkan atribut untuk ditampilkan langsung di halaman yang sama
        model.addAttribute("label", formatLabel);
        model.addAttribute("confidence", result.getConfidence());
        model.addAttribute("imagePath", "/saved-pictures/uploaded/" + uploadedFileName); // Path untuk ditampilkan di browser
        model.addAttribute("diseaseDescription", description);
        model.addAttribute("treatment", treatment);

        // Kembalikan hasil langsung ke halaman result-upload tanpa redirect
        return "result-upload";
    }


    @GetMapping("/result-upload")
    public String showResultForUpload(@ModelAttribute("label") String label, Model model) {
        // Format label sesuai kebutuhan
        String formattedLabel = formatLabel(label);

        // Ambil data deskripsi, perawatan, dan gambar
        String description = descriptionService.getDescription(formattedLabel);
        String treatment = descriptionService.getTreatment(formattedLabel);
        String imagePath = "/saved-pictures/uploaded/uploaded_" + formattedLabel.replaceAll("[^a-zA-Z0-9_-]", "_") + ".jpg";

        // Tambahkan data ke model untuk ditampilkan di halaman
        model.addAttribute("diseaseDescription", description);
        model.addAttribute("formattedLabel", formattedLabel);
        model.addAttribute("treatment", treatment);
        model.addAttribute("imagePath", imagePath);

        // Arahkan ke halaman result-upload
        return "result-upload";
    }

    @PostMapping("/capture")
    public String captureImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        // Lakukan prediksi terlebih dahulu untuk mendapatkan label
        PredictionResult result = predictionService.uploadImage(file);
        String formatLabel = formatLabel(result.getLabel()); // Label hasil prediksi

        // Bentuk nama file berdasarkan label hasil prediksi
        String uploadedFileName = "captured_" + formatLabel.replaceAll("[^a-zA-Z0-9_-]", "_") + ".jpg";
        Path uploadPath = Paths.get("plantapp/src/main/resources/static/saved-pictures/captured/" + uploadedFileName);

        // Simpan file
        Files.write(uploadPath, file.getBytes());

        // Ambil deskripsi dan perawatan berdasarkan label
        String description = descriptionService.getDescription(formatLabel);
        String treatment = descriptionService.getTreatment(formatLabel);

        // Tambahkan atribut untuk ditampilkan langsung di halaman yang sama
        model.addAttribute("label", formatLabel);
        model.addAttribute("confidence", result.getConfidence());
        model.addAttribute("imagePath", "/saved-pictures/captured/" + uploadedFileName);
        model.addAttribute("diseaseDescription", description);
        model.addAttribute("treatment", treatment);

        return "result-capture";
    }

    @GetMapping("/result-capture")
    public String showResultForCapture(@ModelAttribute("label") String label, Model model) {
        // Format label sesuai kebutuhan
        String formattedLabel = formatLabel(label);

        // Ambil data deskripsi, perawatan, dan gambar
        String description = descriptionService.getDescription(formattedLabel);
        String treatment = descriptionService.getTreatment(formattedLabel);
        String imagePath = "/saved-pictures/captured/captured_" + formattedLabel.replaceAll("[^a-zA-Z0-9_-]", "_") + ".jpg";

        // Tambahkan data ke model untuk ditampilkan di halaman
        model.addAttribute("diseaseDescription", description);
        model.addAttribute("formattedLabel", formattedLabel);
        model.addAttribute("treatment", treatment);
        model.addAttribute("imagePath", imagePath);

        return "result-capture";
    }

    private String formatLabel(String rawLabel) {
        return rawLabel
                .replace("___", " - ")
                .replace("_", " ")
                .replace("( ", "(")
                .replace(" )", ")")
                .replaceAll("\\s+", " ")
                .trim();
    }
}

