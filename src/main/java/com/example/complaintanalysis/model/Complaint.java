package com.example.complaintanalysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    private UUID id;
    
    @NotBlank(message = "Teks pengaduan tidak boleh kosong")
    private String text;
    
    private String sentiment;
    private float confidence;
    private LocalDateTime timestamp;
    
    public Complaint(String text) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }
}
