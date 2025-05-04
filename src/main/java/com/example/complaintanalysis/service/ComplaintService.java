package com.example.complaintanalysis.service;

import com.example.complaintanalysis.model.Complaint;
import com.example.complaintanalysis.model.SentimentResult;
import com.example.complaintanalysis.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ComplaintService {
    
    private final ComplaintRepository complaintRepository;
    private final SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, 
                          SentimentAnalysisService sentimentAnalysisService) {
        this.complaintRepository = complaintRepository;
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    public Complaint processComplaint(String complaintText) {
        // Create new complaint
        Complaint complaint = new Complaint(complaintText);
        
        // Analyze sentiment
        try {
            SentimentResult result = sentimentAnalysisService.analyzeSentiment(complaintText);
            complaint.setSentiment(result.getSentiment());
            complaint.setConfidence(result.getConfidence());
        } catch (Exception e) {
            complaint.setSentiment("ERROR");
            complaint.setConfidence(0.0f);
        }
        
        // Save and return
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public List<Complaint> getLatestComplaints(int limit) {
        return complaintRepository.findLatest(limit);
    }

    public Complaint getComplaintById(UUID id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }
}
