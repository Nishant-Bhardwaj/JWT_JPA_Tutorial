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

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    Logger logger = LoggerFactory.getLogger(ComplaintController.class);

    @Cacheable(
            key = "compId",
            value = "ComplaintRecord"
    )
    @GetMapping(name = "/id/{compId}")
    public ComplaintRecord getComplaintById(@PathVariable("compId") String compId){

        Long complaintId = Long.parseLong(compId);

        logger.info(String.format("Complaint Id: %s", complaintId));

        ComplaintRecord complaintRecord = complaintService.getComplaintDetailsById(complaintId);

        if(complaintRecord==null) throw new NoSuchElementException("Complaint not found!!");

        return complaintRecord;
    }

    @PostMapping("/file")
    public ResponseEntity<String> fileComplaint(@RequestBody String complaint) throws JsonProcessingException {

        logger.info(String.format("complaint : %s", complaint));

        ComplaintRecord complaintRecord = complaintService.getInitialComplaintObject(complaint);

        String response = complaintService.fileComplaint(complaintRecord);

        return ResponseEntity.ok(response);
    }
}
