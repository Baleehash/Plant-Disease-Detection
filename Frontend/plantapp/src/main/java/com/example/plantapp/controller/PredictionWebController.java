package com.example.plantapp.controller;

import com.example.plantapp.model.PredictionResult;
import com.example.plantapp.service.DiseaseDescriptionService;
import com.example.plantapp.service.PredictionService;
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
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        String uploadedFileName = "(uploaded)" + file.getOriginalFilename();
        Path uploadPath = Paths.get("plantapp/src/main/resources/static/saved-pictures/" + uploadedFileName);
        Files.write(uploadPath, file.getBytes());

        PredictionResult result = predictionService.uploadImage(file);
        String formatLabel = formatLabel(result.getLabel());
        String description = descriptionService.getDescription(formatLabel);
        String treatment = descriptionService.getTreatment(formatLabel);


        redirectAttributes.addFlashAttribute("label", formatLabel);
        redirectAttributes.addFlashAttribute("confidence", result.getConfidence());
        redirectAttributes.addFlashAttribute("imagePath", "plantapp/src/main/resources/static/saved-pictures/" + uploadedFileName);
        redirectAttributes.addFlashAttribute("diseaseDescription", description);
        redirectAttributes.addFlashAttribute("treatment", treatment);

        return "redirect:/result-upload";
    }

    @GetMapping("/result-upload")
    public String showResultForUpload(@ModelAttribute("label") String label, Model model)  {
        // Format label sesuai kebutuhan
        String formattedLabel = formatLabel(label);

        // Ambil data deskripsi, perawatan, dan gambar
        String description = descriptionService.getDescription(formattedLabel);
        String treatment = descriptionService.getTreatment(formattedLabel);
        String imagePath = descriptionService.findDiseaseImage(formattedLabel);

        // Tambahkan data ke model untuk ditampilkan di halaman
        model.addAttribute("diseaseDescription", description);
        model.addAttribute("formattedLabel", formattedLabel);
        model.addAttribute("treatment", treatment);
        model.addAttribute("imagePath", imagePath);

        // Arahkan ke halaman result-upload
        return "result-upload";
    }


    @PostMapping("/capture")
    public String captureImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        // Simpan gambar yang ditangkap
        String capturedFileName = "(captured)" + file.getOriginalFilename();
        Path capturePath = Paths.get("plantapp/src/main/resources/static/saved-pictures/" + capturedFileName);
        Files.write(capturePath, file.getBytes());

        // Prediksi
        PredictionResult result = predictionService.captureImage(file);

        // Format label untuk deskripsi
        String formatLabel = formatLabel(result.getLabel());
        String description = descriptionService.getDescription(formatLabel);
        String treatment = descriptionService.getTreatment(formatLabel);

        // Tambahkan data untuk ditampilkan di result-upload.html
        redirectAttributes.addFlashAttribute("label", formatLabel);
        redirectAttributes.addFlashAttribute("confidence", result.getConfidence());
        redirectAttributes.addFlashAttribute("imagePath", "plantapp/src/main/resources/static/saved-pictures/" + capturedFileName);
        redirectAttributes.addFlashAttribute("diseaseDescription", description);
        redirectAttributes.addFlashAttribute("treatment", treatment);

        return "redirect:/result-capture";
    }

    @GetMapping("/result-capture")
    public String showResultForCapture(@ModelAttribute("label") String label, Model model) {
        // Format label jika belum diformat
        String formattedLabel = formatLabel(label);
        String description = descriptionService.getDescription(formattedLabel);
        String treatment = descriptionService.getTreatment(formattedLabel);
        String imagePath = descriptionService.findDiseaseImage(formattedLabel);

        // Tambahkan data ke model
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

