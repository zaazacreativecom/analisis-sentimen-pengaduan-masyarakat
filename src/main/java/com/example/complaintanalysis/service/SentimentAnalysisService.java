package com.example.complaintanalysis.service;

import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import com.example.complaintanalysis.model.SentimentResult;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SentimentAnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalysisService.class);
    private static final String MODEL_URL = "https://huggingface.co/indolem/indobert-base-uncased";
    private ZooModel<String, float[]> model;
    private static final List<String> SENTIMENT_LABELS = Arrays.asList("negative", "neutral", "positive");

    @PostConstruct
    public void init() {
        try {
            // Load BERT model for Indonesian language
            Criteria<String, float[]> criteria = Criteria.builder()
                    .setTypes(String.class, float[].class)
                    .optModelUrls(MODEL_URL)
                    .optProgress(new ProgressBar())
                    .build();

            model = criteria.loadModel();
            logger.info("BERT model loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load BERT model", e);
            throw new RuntimeException("Failed to initialize sentiment analysis service", e);
        }
    }

    public SentimentResult analyzeSentiment(String text) {
        try (Predictor<String, float[]> predictor = model.newPredictor()) {
            float[] results = predictor.predict(text);
            
            // Get the index of highest probability
            int maxIndex = 0;
            float maxProb = results[0];
            for (int i = 1; i < results.length; i++) {
                if (results[i] > maxProb) {
                    maxProb = results[i];
                    maxIndex = i;
                }
            }

            String sentiment = SENTIMENT_LABELS.get(maxIndex);
            
            return new SentimentResult(sentiment, maxProb);
        } catch (TranslateException e) {
            logger.error("Error during sentiment prediction", e);
            throw new RuntimeException("Failed to analyze sentiment", e);
        }
    }

    public void cleanup() {
        if (model != null) {
            model.close();
        }
    }
}
