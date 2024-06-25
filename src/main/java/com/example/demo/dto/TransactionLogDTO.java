package com.example.demo.dto;

import lombok.Data;

@Data
public class TransactionLogDTO {

    private String transactionTime;

    private String account_from;

    private String account_to;

    private long amount;

    private String remarks;

    private String reference;
    
}
