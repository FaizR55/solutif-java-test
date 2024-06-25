package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.TransactionLogService;

@RestController
@RequestMapping("/api")
public class TransactionLogController {

    @Autowired
    TransactionLogService transactionLogService;

    ResponseDTO response = new ResponseDTO();

    @GetMapping("/transaction")
    public ResponseDTO getTransaction(@RequestParam(required = true) int page) {
        
        Map<String, Object> data = transactionLogService.getAll(page);

        if(data.get("success").equals(true)){
            // return ResponseEntity
            // .status(HttpStatus.OK)
            // .contentType(MediaType.APPLICATION_JSON)
            // .body(data);

            response.setStatus(true);
            response.setMessage("Success get data");
            response.setData(data);
        }else{
            // return ResponseEntity
            // .status(HttpStatus.BAD_GATEWAY)
            // .contentType(MediaType.APPLICATION_JSON)
            // .body(data);

            response.setStatus(false);
            response.setMessage("Error occured");
            response.setData(data);
        }

        return response;
    }

    @GetMapping("/summary/{account_number}")
    public ResponseDTO getSummary(@PathVariable("account_number") String account_number) {

        Map<String, Object> data = transactionLogService.getSummary(account_number);

        if(data.get("success").equals(true)){
            // return ResponseEntity
            // .status(HttpStatus.OK)
            // .contentType(MediaType.APPLICATION_JSON)
            // .body(data);

            response.setStatus(true);
            response.setMessage("Success get data");
            response.setData(data);
        }else{
            // return ResponseEntity
            // .status(HttpStatus.BAD_GATEWAY)
            // .contentType(MediaType.APPLICATION_JSON)
            // .body(data);

            response.setStatus(false);
            response.setMessage("Error occured");
            response.setData(data);
        }
        
        return response;
    }

}
