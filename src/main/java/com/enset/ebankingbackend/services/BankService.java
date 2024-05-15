package com.enset.ebankingbackend.services;

import com.enset.ebankingbackend.Entities.BankAccount;
import com.enset.ebankingbackend.Entities.CurrentAccount;
import com.enset.ebankingbackend.Entities.SavingAccount;
import com.enset.ebankingbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("242ba129-eaf7-45df-9842-d26c12cd9025").orElse(null);
        if (bankAccount != null){
            System.out.println("**************************");
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getStatusAccount());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("overdraft: "+((CurrentAccount)bankAccount).getOverDraft());
            }else if(bankAccount instanceof SavingAccount)
                System.out.println("interest rate: "+((SavingAccount)bankAccount).getInterestRate());
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println("-----------------------------");
                System.out.println(op.getAmount()+"\t"+op.getOperationDate()+"\t"+op.getOperationType());
            });
        };

    }
}
