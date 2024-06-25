package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionLog;
import com.example.demo.repository.TransactionLogRepo;

@Service
public class TransactionLogService {

    @Autowired
    TransactionLogRepo transactionLogRepo;

    public Map<String, Object> getAll(int page){

        Map<String, Object> response = new HashMap<>();

        try {
            PageRequest pagination = PageRequest.of(page, 10);
            
            List<Object> list = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Page<TransactionLog> data = transactionLogRepo.getSuccessTransactions(pagination);
            for (TransactionLog t : data.getContent()) {
                Map<String, Object> transactionData = new HashMap<>();
                transactionData.put("transaction_time", formatter.format(t.getCreated_date()));
                transactionData.put("account_from", t.getAccount_from());
                transactionData.put("account_to", t.getAccount_to());
                transactionData.put("amount", t.getAmount());
                transactionData.put("remarks", t.getRemarks());
                transactionData.put("reference", t.getExternal_reference());
                list.add(transactionData);
            }

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("request_time", formatter.format(new Date()));
            metadata.put("currentPage", page);
            metadata.put("dataPage", data.getSize());
            metadata.put("totalPage", data.getTotalPages());
            metadata.put("totalData", data.getTotalElements());

            response.put("data", list);
            response.put("metadata", metadata);
            response.put("success", true);
        } catch (Exception e) {
            System.out.println("error occured: " + e.getMessage());
            response.put("data", "error occured: " + e.getMessage());
            response.put("metadata", null);
            response.put("success", false);
        }

        return response;
    }
    
    public Map<String, Object> getSummary(String account_number){

        Map<String, Object> response = new HashMap<>();

        try {
            List<TransactionLog> data = transactionLogRepo.getSuccessTransactionsFromAccount(account_number);

            int topupTransferSum = 0;
            int otherSum = 0;
            for (TransactionLog t : data) {
                String remarks = t.getRemarks();
                long amount = t.getAmount();

                if ("topup_transfer".equals(remarks)) {
                    topupTransferSum += amount;
                } else {
                    otherSum += amount;
                }
            }
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            long totalBalance = topupTransferSum - otherSum;
            Map<String, Object> response_data = new HashMap<>();
            response_data.put("request_time", formatter.format(new Date()));
            response_data.put("account_number", account_number);
            response_data.put("total_balance", totalBalance);

            response.put("success", true);
            response.put("data", response_data);
        } catch (Exception e) {
            System.out.println("error occured: " + e.getMessage());
            response.put("data", "error occured: " + e.getMessage());
            response.put("success", false);
        }
        

        return response;
    }
}
