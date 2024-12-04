package com.example.plantapp.model;

public class PredictionResult {
    private String filename;
    private String label;
    private float confidence;

    // Constructor
    public PredictionResult() {
    }

    public PredictionResult(String filename, String label, float confidence) {
        this.filename = filename;
        this.label = label;
        this.confidence = confidence;
    }

    // Getter dan Setter
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "PredictionResult{" +
                "filename='" + filename + '\'' +
                ", label='" + label + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
