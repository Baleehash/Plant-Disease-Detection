package com.example.plantapp.service;

import com.example.plantapp.model.PredictionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.example.plantapp.util.MultipartInputStreamFileResource;


import java.io.IOException;

@Service
public class PredictionService {

    @Value("${fastapi.url}") // URL backend FastAPI dari application.properties
    private String fastApiUrl;

    private final RestTemplate restTemplate;

    public PredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PredictionResult uploadImage(MultipartFile file) throws IOException {
        String url = fastApiUrl + "/predict/upload";

        // Menyiapkan header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Menyiapkan body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Kirim request ke backend FastAPI
        ResponseEntity<PredictionResult> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PredictionResult.class
        );

        return response.getBody();
    }

    public PredictionResult captureImage(MultipartFile file) throws IOException {
        String url = fastApiUrl + "/predict/capture";

        // Menyiapkan header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Menyiapkan body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Kirim request ke backend FastAPI
        ResponseEntity<PredictionResult> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PredictionResult.class
        );

        return response.getBody();
    }
}
