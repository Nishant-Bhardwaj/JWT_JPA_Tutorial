package com.nishant.jpa.complete.tutorial.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.jpa.complete.tutorial.model.ComplaintRecord;
import com.nishant.jpa.complete.tutorial.model.Department;
import com.nishant.jpa.complete.tutorial.repository.ComplaintRecordRepository;
import com.nishant.jpa.complete.tutorial.repository.UserRecordRepository;
import com.nishant.jpa.complete.tutorial.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRecordRepository complaintRecordRepository;

    @Autowired
    UserRecordRepository userRecordRepository;

    @Override
    public ComplaintRecord getComplaintDetailsById(Long complaintId) {
        return complaintRecordRepository.findById(complaintId).orElse(null);
    }

    @Override
    public String fileComplaint(ComplaintRecord complaintRecord) {

        var complaintRecordAfterSave = complaintRecordRepository.saveAndFlush(complaintRecord);

        if(complaintRecordAfterSave.getComplaintId()!=null)
            return "Complaint Filed, please note id for reference: " + complaintRecordAfterSave.getComplaintId();

        else
            return "Complaint not Filed, please try again later";
    }

    @Override
    public ComplaintRecord getInitialComplaintObject(String complaint) throws JsonProcessingException {

        var objectMapper = new ObjectMapper();

        var jsonNode = objectMapper.readTree(complaint);

        var department = Department.builder()
                .departmentName(jsonNode.get("complaintDepartment").asText())
                .build();

        var userRecord = userRecordRepository.getByUsername(jsonNode.get("username").asText());

        return  ComplaintRecord.builder()
                .status("assigned")
                .complaintAssignedTo(jsonNode.get("complaintAssignedTo").asText())
                .complaintSubject(jsonNode.get("complaintSubject").asText())
                .complaintBody(jsonNode.get("complaintBody").asText())
                .complaintDepartment(department)
                .userRecord(userRecord)
                .build();

    }
}
