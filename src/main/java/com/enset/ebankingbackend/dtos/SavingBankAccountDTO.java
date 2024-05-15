package com.enset.ebankingbackend.dtos;


import com.enset.ebankingbackend.enums.StatusAccount;
import lombok.Data;
import java.util.Date;


@Data
public class SavingBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date createdAt;
    private StatusAccount statusAccount;
    private CustomerDTO customerDTO;
    private double interestRate;

}
