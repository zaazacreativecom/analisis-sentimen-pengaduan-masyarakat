package com.example.complaintanalysis.repository;

import com.example.complaintanalysis.model.Complaint;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ComplaintRepository {
    private final List<Complaint> complaints = new CopyOnWriteArrayList<>();
    
    public Complaint save(Complaint complaint) {
        complaints.add(complaint);
        return complaint;
    }
    
    public List<Complaint> findAll() {
        return new ArrayList<>(complaints);
    }
    
    public Optional<Complaint> findById(UUID id) {
        return complaints.stream()
                .filter(complaint -> complaint.getId().equals(id))
                .findFirst();
    }
    
    public void deleteById(UUID id) {
        complaints.removeIf(complaint -> complaint.getId().equals(id));
    }
    
    public List<Complaint> findLatest(int limit) {
        return complaints.stream()
                .sorted((c1, c2) -> c2.getTimestamp().compareTo(c1.getTimestamp()))
                .limit(limit)
                .collect(java.util.stream.Collectors.toList());
    }
}
