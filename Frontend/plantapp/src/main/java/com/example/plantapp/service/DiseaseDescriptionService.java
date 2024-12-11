package com.example.plantapp.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DiseaseDescriptionService {
    private final Map<String, String> diseaseDescriptions;

    public DiseaseDescriptionService() {
        diseaseDescriptions = new HashMap<>();

        // Apple diseases
        diseaseDescriptions.put(formatLabel("Apple___Apple_Scab"), "A fungal disease causing dark, scabby lesions on leaves, fruit, and stems.");
        diseaseDescriptions.put(formatLabel("Apple___Black_Rot"), "A fungal infection that leads to fruit rotting, leaf spots, and canker formation on branches.");
        diseaseDescriptions.put(formatLabel("Apple___Cedar_Apple_Rust"), "A fungal disease involving orange, gelatinous galls on cedar trees and rust-like spots on apple leaves and fruit.");
        diseaseDescriptions.put(formatLabel("Apple___Healthy"), "Apple plants free from visible signs of diseases or stress.");

        // Blueberry
        diseaseDescriptions.put(formatLabel("Blueberry___Healthy"), "Blueberry plants with no signs of disease or damage.");

        // Cherry
        diseaseDescriptions.put(formatLabel("Cherry_(including_sour)___Powdery_Mildew"), "A fungal infection forming white, powdery patches on leaves, stems, and fruit.");
        diseaseDescriptions.put(formatLabel("Cherry_(including_sour)___Healthy"), "Cherry plants free from disease and showing normal growth.");

        // Corn (maize)
        diseaseDescriptions.put(formatLabel("Corn_(maize)___Cercospora_Leaf_Spot_Gray_Leaf_Spot"), "A fungal disease causing rectangular gray or brown lesions on leaves.");
        diseaseDescriptions.put(formatLabel("Corn_(maize)___Common_Rust"), "A fungal infection producing reddish-brown pustules on leaves.");
        diseaseDescriptions.put(formatLabel("Corn_(maize)___Northern_Leaf_Blight"), "A fungal disease characterized by large, elliptical tan lesions on leaves.");
        diseaseDescriptions.put(formatLabel("Corn_(maize)___Healthy"), "Corn plants without visible signs of disease or stress.");

        // Grape
        diseaseDescriptions.put(formatLabel("Grape___Black_Rot"), "A fungal disease causing dark spots on leaves and black shriveled fruit.");
        diseaseDescriptions.put(formatLabel("Grape___Esca_(Black_Measles)"), "A complex disease causing leaf discoloration, black streaks on wood, and dried berries.");
        diseaseDescriptions.put(formatLabel("Grape___Leaf_Blight_(Isariopsis_Leaf_Spot)"), "A fungal infection leading to brown, necrotic spots on leaves.");
        diseaseDescriptions.put(formatLabel("Grape___Healthy"), "Grapevines with no signs of disease or damage.");

        // Orange
        diseaseDescriptions.put(formatLabel("Orange___Haunglongbing_(Citrus_Greening)"), "A bacterial disease causing yellowing leaves, deformed fruit, and tree decline.");

        // Peach
        diseaseDescriptions.put(formatLabel("Peach___Bacterial_Spot"), "A bacterial infection causing dark spots on leaves, fruits, and twigs.");
        diseaseDescriptions.put(formatLabel("Peach___Healthy"), "Peach trees showing no disease symptoms and healthy growth.");

        // Pepper, bell
        diseaseDescriptions.put(formatLabel("Pepper,_bell___Bacterial_Spot"), "A bacterial disease causing water-soaked spots on leaves and fruit, which turn dark.");
        diseaseDescriptions.put(formatLabel("Pepper,_bell___Healthy"), "Bell pepper plants without disease symptoms or stress.");

        // Potato
        diseaseDescriptions.put(formatLabel("Potato___Early_Blight"), "A fungal disease causing brown concentric rings on leaves and tubers.");
        diseaseDescriptions.put(formatLabel("Potato___Late_Blight"), "A fungal disease leading to water-soaked, dark lesions on leaves and tubers.");
        diseaseDescriptions.put(formatLabel("Potato___Healthy"), "Potato plants free from disease or visible damage.");

        // Raspberry
        diseaseDescriptions.put(formatLabel("Raspberry___Healthy"), "Raspberry plants with no visible disease symptoms.");

        // Soybean
        diseaseDescriptions.put(formatLabel("Soybean___Healthy"), "Soybean plants free from disease or stress.");

        // Squash
        diseaseDescriptions.put(formatLabel("Squash___Powdery_Mildew"), "A fungal infection causing white, powdery growth on leaves and stems.");

        // Strawberry
        diseaseDescriptions.put(formatLabel("Strawberry___Leaf_Scorch"), "A fungal disease causing red to purple spots on leaves that may merge, leading to leaf burn.");
        diseaseDescriptions.put(formatLabel("Strawberry___Healthy"), "Strawberry plants without disease symptoms or visible stress.");

        // Tomato
        diseaseDescriptions.put(formatLabel("Tomato___Bacterial_Spot"), "A bacterial infection causing small, water-soaked spots that turn dark on leaves and fruit.");
        diseaseDescriptions.put(formatLabel("Tomato___Early_Blight"), "A fungal disease causing concentric rings on lower leaves and stem lesions.");
        diseaseDescriptions.put(formatLabel("Tomato___Late_Blight"), "A fungal disease causing water-soaked lesions on leaves and dark spots on fruit.");
        diseaseDescriptions.put(formatLabel("Tomato___Leaf_Mold"), "A fungal disease resulting in yellow spots on the upper leaf surface and mold on the underside.");
        diseaseDescriptions.put(formatLabel("Tomato___Septoria_Leaf_Spot"), "A fungal disease causing small, circular spots on leaves with dark margins.");
        diseaseDescriptions.put(formatLabel("Tomato___Spider_Mites_Two-spotted_Spider_Mite"), "A pest causing leaf stippling and webbing, leading to leaf yellowing and death.");
        diseaseDescriptions.put(formatLabel("Tomato___Target_Spot"), "A fungal disease causing circular spots with a target-like pattern on leaves and stems.");
        diseaseDescriptions.put(formatLabel("Tomato___Tomato_Yellow_Leaf_Curl_Virus"), "A viral infection causing curled, yellowing leaves and stunted growth.");
        diseaseDescriptions.put(formatLabel("Tomato___Tomato_Mosaic_Virus"), "A viral disease causing mottled, yellowish leaves and reduced fruit yield.");
        diseaseDescriptions.put(formatLabel("Tomato___Healthy"), "Tomato plants with no disease or damage signs.");
    }

    public String getDescription(String rawLabel) {
        String formattedLabel = formatLabel(rawLabel);
        System.out.println("Formatted Label: " + formattedLabel);

        // Case insensitive lookup
        Map<String, String> caseInsensitiveMap = diseaseDescriptions.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(), Map.Entry::getValue));
        String description = caseInsensitiveMap.get(formattedLabel.toLowerCase());

        if (description == null) {
            System.out.println("Description not found for formatted label: " + formattedLabel);
            return "No description available for this disease.";
        }
        return description;
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


