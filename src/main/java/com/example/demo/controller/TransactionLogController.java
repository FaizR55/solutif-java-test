package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TransactionLogService;

@SuppressWarnings({"rawtypes"})
@RestController
@RequestMapping("/api")
public class TransactionLogController {

    @Autowired
    TransactionLogService transactionLogService;

    @GetMapping("/transaction")
    public ResponseEntity getTransaction(@RequestParam(required = true) int page) {
        
        Map<String, Object> data = transactionLogService.getAll(page);

        if(data.get("success").equals(true)){
            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(data);
        }else{
            return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(data);
        }

    }

    @GetMapping("/summary/{account_number}")
    public ResponseEntity getSummary(@PathVariable("account_number") String account_number) {

        Map<String, Object> data = transactionLogService.getSummary(account_number);

        if(data.get("success").equals(true)){
            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(data);
        }else{
            return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(data);
        }
    }

}
