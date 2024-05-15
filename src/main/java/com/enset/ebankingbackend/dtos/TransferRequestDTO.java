package com.enset.ebankingbackend.dtos;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private String description;
}
