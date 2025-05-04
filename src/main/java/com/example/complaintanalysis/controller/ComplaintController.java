package com.example.complaintanalysis.controller;

import com.example.complaintanalysis.model.Complaint;
import com.example.complaintanalysis.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("complaints", complaintService.getLatestComplaints(10));
        return "index";
    }

    @PostMapping("/analyze")
    public String analyzeComplaint(@Valid @ModelAttribute("complaint") Complaint complaint,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("complaints", complaintService.getLatestComplaints(10));
            return "index";
        }

        Complaint processedComplaint = complaintService.processComplaint(complaint.getText());
        return "redirect:/result/" + processedComplaint.getId();
    }

    @GetMapping("/result/{id}")
    public String showResult(@PathVariable UUID id, Model model) {
        try {
            Complaint complaint = complaintService.getComplaintById(id);
            model.addAttribute("complaint", complaint);
            model.addAttribute("sentimentClass", getSentimentClass(complaint.getSentiment()));
            return "result";
        } catch (RuntimeException e) {
            return "redirect:/";
        }
    }

    private String getSentimentClass(String sentiment) {
        return switch (sentiment.toLowerCase()) {
            case "positive" -> "bg-green-100 text-green-800";
            case "negative" -> "bg-red-100 text-red-800";
            default -> "bg-gray-100 text-gray-800";
        };
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        model.addAttribute("complaints", complaintService.getAllComplaints());
        return "history";
    }
}
