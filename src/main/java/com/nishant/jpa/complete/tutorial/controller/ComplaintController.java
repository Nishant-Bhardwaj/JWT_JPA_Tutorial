package com.nishant.jpa.complete.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nishant.jpa.complete.tutorial.model.ComplaintRecord;
import com.nishant.jpa.complete.tutorial.service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    Logger logger = LoggerFactory.getLogger(ComplaintController.class);

    @Cacheable(
            key = "#complaintId",
            value = "ComplaintRecord"
    )
    @GetMapping(value = "/id/{complaintId}")
    public ComplaintRecord getComplaintById(@PathVariable("complaintId") Long complaintId){

        logger.info(String.format("Complaint Id: %s", complaintId));

        var complaintRecord = complaintService.getComplaintDetailsById(complaintId);

        logger.info("complaintRecord : "+ complaintRecord.getComplaintId());

        return complaintRecord;
    }

    @PostMapping("/file")
    public ResponseEntity<String> fileComplaint(@RequestBody String complaint) throws JsonProcessingException {

        logger.info(String.format("complaint : %s", complaint));

        var complaintRecord = complaintService.getInitialComplaintObject(complaint);

        String response = complaintService.fileComplaint(complaintRecord);

        return ResponseEntity.ok(response);
    }
}
