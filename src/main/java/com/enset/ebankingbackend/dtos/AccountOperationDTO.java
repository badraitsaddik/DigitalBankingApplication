package com.enset.ebankingbackend.dtos;
import com.enset.ebankingbackend.enums.OperationType;
import lombok.Data;
import java.util.Date;


@Data

public class AccountOperationDTO {
    private Long id;
    private double amount;
    private Date operationDate;
    private OperationType operationType;
    private String description;
}
