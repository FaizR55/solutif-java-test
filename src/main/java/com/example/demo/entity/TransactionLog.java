package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction_log")
public class TransactionLog {

    @Id
    @Column(columnDefinition = "clob")
    private String id;

    private String internal_reference;

    private String external_reference;

    private String account_from;
    
    private String account_to;
    
    private long amount;

    private String remarks;

    private Boolean success;

    private Date created_date;
    
}
