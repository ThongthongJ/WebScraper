package com.example.websrcapper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "tr_transaction")
@IdClass(LebelEntity.class)
@Data
public class TrTransactionEntity {
    @Id
    private String symbol;
    @Id
    private Date transactionDate;
    private Double openPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double closePrice;
    private Double changeRatio;
    private Double noOfStock;
    private Double volume;
    private String reason;
    private String status;
    private BigInteger batchId;
}
