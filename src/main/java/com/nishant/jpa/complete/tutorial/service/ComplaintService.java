package com.nishant.jpa.complete.tutorial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nishant.jpa.complete.tutorial.model.ComplaintRecord;
import org.springframework.stereotype.Service;

@Service
public interface ComplaintService {
    ComplaintRecord getComplaintDetailsById(Long complaintId);

    String fileComplaint(ComplaintRecord complaintRecord);

    ComplaintRecord getInitialComplaintObject(String complaint) throws JsonProcessingException;
}
