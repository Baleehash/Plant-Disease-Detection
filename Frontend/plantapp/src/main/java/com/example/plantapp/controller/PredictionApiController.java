package com.example.plantapp.controller;

import com.example.plantapp.model.PredictionResult;
import com.example.plantapp.service.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PredictionApiController {

    private final PredictionService predictionService;

    public PredictionApiController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<PredictionResult> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        PredictionResult result = predictionService.uploadImage(file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/capture")
    public ResponseEntity<PredictionResult> captureImage(@RequestParam("file") MultipartFile file) throws IOException {
        PredictionResult result = predictionService.captureImage(file);
        return ResponseEntity.ok(result);
    }
}
