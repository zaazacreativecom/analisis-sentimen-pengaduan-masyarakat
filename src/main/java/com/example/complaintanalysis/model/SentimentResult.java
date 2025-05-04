package com.example.complaintanalysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SentimentResult {
    private String sentiment;
    private float confidence;
    
    // Additional fields for more detailed analysis
    private String originalText;
    private String processedText;
    
    public SentimentResult(String sentiment, float confidence) {
        this.sentiment = sentiment;
        this.confidence = confidence;
    }
}
