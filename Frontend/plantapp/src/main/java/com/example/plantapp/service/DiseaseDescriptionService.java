package com.example.plantapp.service;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.File;

@Component
public class DiseaseDescriptionService {
    private final Map<String, String> diseaseDescriptions;
    private final Map<String, String> diseaseTreatments;

    private String formatLabel(String rawLabel) {
        return rawLabel
                .replace("___", " - ")
                .replace("_", " ")
                .replace("( ", "(")
                .replace(" )", ")")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public DiseaseDescriptionService() {
        diseaseDescriptions = new HashMap<>();
        diseaseTreatments = new HashMap<>();

        // Apple diseases
        diseaseDescriptions.put(formatLabel("Apple___Apple_Scab"), "A fungal disease causing dark, scabby lesions on leaves, fruit, and stems.");
        diseaseTreatments.put(formatLabel("Apple___Apple_Scab"), "Remove infected leaves and fruit promptly. Apply appropriate fungicides during the growing season.");

        diseaseDescriptions.put(formatLabel("Apple___Black_Rot"), "A fungal infection that leads to fruit rotting, leaf spots, and canker formation on branches.");
        diseaseTreatments.put(formatLabel("Apple___Black_Rot"), "Prune infected branches and apply fungicides. Practice good sanitation by removing fallen leaves and fruit.");

        diseaseDescriptions.put(formatLabel("Apple___Cedar_Apple_Rust"), "A fungal disease involving orange, gelatinous galls on cedar trees and rust-like spots on apple leaves and fruit.");
        diseaseTreatments.put(formatLabel("Apple___Cedar_Apple_Rust"), "Remove galls from cedar trees and apply fungicides on apple trees early in the season.");

        diseaseDescriptions.put(formatLabel("Apple___Healthy"), "Apple plants free from visible signs of diseases or stress.");
        diseaseTreatments.put(formatLabel("Apple___Healthy"), "No treatment required. Continue regular care and monitoring.");

        // Blueberry
        diseaseDescriptions.put(formatLabel("Blueberry___Healthy"), "Blueberry plants with no signs of disease or damage.");
        diseaseTreatments.put(formatLabel("Blueberry___Healthy"), "No treatment required. Ensure proper care and monitoring.");

        // Cherry
        diseaseDescriptions.put(formatLabel("Cherry_(including_sour)___Powdery_Mildew"), "A fungal infection forming white, powdery patches on leaves, stems, and fruit.");
        diseaseTreatments.put(formatLabel("Cherry_(including_sour)___Powdery_Mildew"), "Apply fungicides and prune affected parts. Ensure good air circulation around the plant.");

        diseaseDescriptions.put(formatLabel("Cherry_(including_sour)___Healthy"), "Cherry plants free from disease and showing normal growth.");
        diseaseTreatments.put(formatLabel("Cherry_(including_sour)___Healthy"), "No treatment required. Maintain optimal growing conditions.");

        // Corn (maize)
        diseaseDescriptions.put(formatLabel("Corn_(maize)___Cercospora_Leaf_Spot_Gray_Leaf_Spot"), "A fungal disease causing rectangular gray or brown lesions on leaves.");
        diseaseTreatments.put(formatLabel("Corn_(maize)___Cercospora_Leaf_Spot_Gray_Leaf_Spot"), "Plant resistant varieties and apply fungicides when necessary.");

        diseaseDescriptions.put(formatLabel("Corn_(maize)___Common_Rust"), "A fungal infection producing reddish-brown pustules on leaves.");
        diseaseTreatments.put(formatLabel("Corn_(maize)___Common_Rust"), "Apply fungicides if the disease is severe. Grow resistant hybrids and practice crop rotation.");

        diseaseDescriptions.put(formatLabel("Corn_(maize)___Northern_Leaf_Blight"), "A fungal disease characterized by large, elliptical tan lesions on leaves.");
        diseaseTreatments.put(formatLabel("Corn_(maize)___Northern_Leaf_Blight"), "Plant resistant varieties, use crop rotation, and apply fungicides if necessary.");

        diseaseDescriptions.put(formatLabel("Corn_(maize)___Healthy"), "Corn plants without visible signs of disease or stress.");
        diseaseTreatments.put(formatLabel("Corn_(maize)___Healthy"), "No treatment required. Maintain optimal growing conditions.");

        // Grape
        diseaseDescriptions.put(formatLabel("Grape___Black_Rot"), "A fungal disease causing dark spots on leaves and black shriveled fruit.");
        diseaseTreatments.put(formatLabel("Grape___Black_Rot"), "Prune and remove infected parts. Apply fungicides before symptoms appear.");

        diseaseDescriptions.put(formatLabel("Grape___Esca_(Black_Measles)"), "A complex disease causing leaf discoloration, black streaks on wood, and dried berries.");
        diseaseTreatments.put(formatLabel("Grape___Esca_(Black_Measles)"), "Avoid overwatering and excessive pruning. Remove and destroy infected wood.");

        diseaseDescriptions.put(formatLabel("Grape___Leaf_Blight_(Isariopsis_Leaf_Spot)"), "A fungal infection leading to brown, necrotic spots on leaves.");
        diseaseTreatments.put(formatLabel("Grape___Leaf_Blight_(Isariopsis_Leaf_Spot)"), "Prune affected areas and apply fungicides. Ensure proper air circulation.");

        diseaseDescriptions.put(formatLabel("Grape___Healthy"), "Grapevines with no signs of disease or damage.");
        diseaseTreatments.put(formatLabel("Grape___Healthy"), "No treatment required. Continue monitoring and good vineyard management.");

        // Orange
        diseaseDescriptions.put(formatLabel("Orange___Haunglongbing_(Citrus_Greening)"), "A bacterial disease causing yellowing leaves, deformed fruit, and tree decline.");
        diseaseTreatments.put(formatLabel("Orange___Haunglongbing_(Citrus_Greening)"), "Remove infected trees and control psyllid vectors. Use disease-free planting material.");

        // Peach
        diseaseDescriptions.put(formatLabel("Peach___Bacterial_Spot"), "A bacterial infection causing dark spots on leaves, fruits, and twigs.");
        diseaseTreatments.put(formatLabel("Peach___Bacterial_Spot"), "Apply bactericides and prune affected areas. Avoid overhead watering.");

        diseaseDescriptions.put(formatLabel("Peach___Healthy"), "Peach trees showing no disease symptoms and healthy growth.");
        diseaseTreatments.put(formatLabel("Peach___Healthy"), "No treatment required. Maintain regular care and monitoring.");

        // Pepper, bell
        diseaseDescriptions.put(formatLabel("Pepper,_bell___Bacterial_Spot"), "A bacterial disease causing water-soaked spots on leaves and fruit, which turn dark.");
        diseaseTreatments.put(formatLabel("Pepper,_bell___Bacterial_Spot"), "Apply bactericides and avoid overhead watering. Remove infected plants.");

        diseaseDescriptions.put(formatLabel("Pepper,_bell___Healthy"), "Bell pepper plants without disease symptoms or stress.");
        diseaseTreatments.put(formatLabel("Pepper,_bell___Healthy"), "No treatment required. Monitor growth and maintain proper care.");

        // Potato
        diseaseDescriptions.put(formatLabel("Potato___Early_Blight"), "A fungal disease causing brown concentric rings on leaves and tubers.");
        diseaseTreatments.put(formatLabel("Potato___Early_Blight"), "Remove affected leaves and apply fungicides. Practice crop rotation.");

        diseaseDescriptions.put(formatLabel("Potato___Late_Blight"), "A fungal disease leading to water-soaked, dark lesions on leaves and tubers.");
        diseaseTreatments.put(formatLabel("Potato___Late_Blight"), "Remove infected plants and apply fungicides. Avoid overwatering.");

        diseaseDescriptions.put(formatLabel("Potato___Healthy"), "Potato plants free from disease or visible damage.");
        diseaseTreatments.put(formatLabel("Potato___Healthy"), "No treatment required. Maintain healthy growth practices.");

        // Raspberry
        diseaseDescriptions.put(formatLabel("Raspberry___Healthy"), "Raspberry plants with no visible disease symptoms.");
        diseaseTreatments.put(formatLabel("Raspberry___Healthy"), "No treatment required. Continue monitoring and proper care.");

        // Soybean
        diseaseDescriptions.put(formatLabel("Soybean___Healthy"), "Soybean plants free from disease or stress.");
        diseaseTreatments.put(formatLabel("Soybean___Healthy"), "No treatment required. Ensure optimal growing conditions.");

        // Squash
        diseaseDescriptions.put(formatLabel("Squash___Powdery_Mildew"), "A fungal infection causing white, powdery growth on leaves and stems.");
        diseaseTreatments.put(formatLabel("Squash___Powdery_Mildew"), "Apply fungicides and ensure proper air circulation. Remove infected leaves.");

        // Strawberry
        diseaseDescriptions.put(formatLabel("Strawberry___Leaf_Scorch"), "A fungal disease causing red to purple spots on leaves that may merge, leading to leaf burn.");
        diseaseTreatments.put(formatLabel("Strawberry___Leaf_Scorch"), "Remove infected leaves and apply fungicides. Avoid overhead watering.");

        diseaseDescriptions.put(formatLabel("Strawberry___Healthy"), "Strawberry plants without disease symptoms or visible stress.");
        diseaseTreatments.put(formatLabel("Strawberry___Healthy"), "No treatment required. Maintain good cultivation practices.");

        // Tomato
        diseaseDescriptions.put(formatLabel("Tomato___Bacterial_Spot"), "A bacterial infection causing small, water-soaked spots that turn dark on leaves and fruit.");
        diseaseTreatments.put(formatLabel("Tomato___Bacterial_Spot"), "Remove infected leaves and avoid overhead watering. Apply copper-based bactericides.");

        diseaseDescriptions.put(formatLabel("Tomato___Early_Blight"), "A fungal disease causing concentric rings on lower leaves and stem lesions.");
        diseaseTreatments.put(formatLabel("Tomato___Early_Blight"), "Prune affected leaves and apply fungicides. Practice crop rotation.");

        diseaseDescriptions.put(formatLabel("Tomato___Late_Blight"), "A fungal disease causing water-soaked lesions on leaves and dark spots on fruit.");
        diseaseTreatments.put(formatLabel("Tomato___Late_Blight"), "Remove infected plants and apply fungicides. Avoid excessive leaf wetness.");

        diseaseDescriptions.put(formatLabel("Tomato___Leaf_Mold"), "A fungal disease resulting in yellow spots on the upper leaf surface and mold on the underside.");
        diseaseTreatments.put(formatLabel("Tomato___Leaf_Mold"), "Remove affected leaves and improve air circulation. Apply fungicides as needed.");

        diseaseDescriptions.put(formatLabel("Tomato___Septoria_Leaf_Spot"), "A fungal disease causing small, circular spots on leaves with dark margins.");
        diseaseTreatments.put(formatLabel("Tomato___Septoria_Leaf_Spot"), "Prune infected leaves and apply fungicides. Avoid overhead watering.");

        diseaseDescriptions.put(formatLabel("Tomato___Spider_Mites_Two-spotted_Spider_Mite"), "A pest causing leaf stippling and webbing, leading to leaf yellowing and death.");
        diseaseTreatments.put(formatLabel("Tomato___Spider_Mites_Two-spotted_Spider_Mite"), "Introduce natural predators like ladybugs or apply miticides.");

        diseaseDescriptions.put(formatLabel("Tomato___Target_Spot"), "A fungal disease causing circular spots with a target-like pattern on leaves and stems.");
        diseaseTreatments.put(formatLabel("Tomato___Target_Spot"), "Remove affected leaves and apply fungicides. Maintain proper spacing between plants.");

        diseaseDescriptions.put(formatLabel("Tomato___Tomato_Yellow_Leaf_Curl_Virus"), "A viral infection causing curled, yellowing leaves and stunted growth.");
        diseaseTreatments.put(formatLabel("Tomato___Tomato_Yellow_Leaf_Curl_Virus"), "Control whiteflies, the primary vector. Remove infected plants.");

        diseaseDescriptions.put(formatLabel("Tomato___Tomato_Mosaic_Virus"), "A viral disease causing mottled, yellowish leaves and reduced fruit yield.");
        diseaseTreatments.put(formatLabel("Tomato___Tomato_Mosaic_Virus"), "Remove infected plants and sanitize tools. Avoid handling plants when wet.");

        diseaseDescriptions.put(formatLabel("Tomato___Healthy"), "Tomato plants with no disease or damage signs.");
        diseaseTreatments.put(formatLabel("Tomato___Healthy"), "No treatment required. Ensure good cultural practices and monitoring.");

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

    public String getTreatment(String rawLabel) {
        String formattedLabel = formatLabel(rawLabel);
        System.out.println("Formatted Label for Treatment: " + formattedLabel);

        // Case insensitive lookup
        Map<String, String> caseInsensitiveMap = diseaseTreatments.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(), Map.Entry::getValue));
        String treatment = caseInsensitiveMap.get(formattedLabel.toLowerCase());

        if (treatment == null) {
            System.out.println("Treatment not found for formatted label: " + formattedLabel);
            return "No treatment information available for this disease.";
        }
        return treatment;
    }

    public String findDiseaseImage(String originalFileName, String type) {
        // Folder dasar untuk gambar (uploaded atau captured)
        String basePath = "src/main/resources/static/saved-pictures/" + type + "/";
        String prefixedFileName = (type.equals("uploaded") ? "uploaded_" : "captured_") + originalFileName;

        // Coba cari file berdasarkan nama asli dengan berbagai ekstensi
        Path filePath = Paths.get(basePath + prefixedFileName);
        System.out.println("Checking file: " + filePath.toAbsolutePath()); // Debugging
        if (Files.exists(filePath)) {
            // Mengembalikan path relatif untuk browser
            return "/saved-pictures/" + type + "/" + prefixedFileName;
        }

        // Gambar default jika file tidak ditemukan
        return "/images/disease/apple_scab.jpg";
    }
}