package com.enset.ebankingbackend;

import com.enset.ebankingbackend.Entities.*;
import com.enset.ebankingbackend.dtos.BankAccountDTO;
import com.enset.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.enset.ebankingbackend.dtos.CustomerDTO;
import com.enset.ebankingbackend.dtos.SavingBankAccountDTO;
import com.enset.ebankingbackend.enums.OperationType;
import com.enset.ebankingbackend.enums.StatusAccount;
import com.enset.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.enset.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import com.enset.ebankingbackend.repositories.AccountOperationRepository;
import com.enset.ebankingbackend.repositories.BankAccountRepository;
import com.enset.ebankingbackend.repositories.CustomerRepository;
import com.enset.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("ayman","mohamed","ali","ahmed").forEach(c->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(c);
                customer.setEmail(c+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCostumers().forEach(cst->{
                try {
                    bankAccountService.savecurrentBankAccount(Math.random()*90000,9000,cst.getId());
                    bankAccountService.savesavingBankAccount(Math.random()*120000,5.5,cst.getId());


                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }

            });
            List<BankAccountDTO> bankAccounts= bankAccountService.listBankAccounts();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if(bankAccount instanceof CurrentBankAccountDTO){
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }else{
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"credit");
                    bankAccountService.debit(accountId,10000+Math.random()*9000,"debit");
                }

            }
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){

        return args -> {
            Stream.of("ayman","mohamed","ali","ahmed").forEach(c->{
                Customer customer = new Customer();
                customer.setName(c);
                customer.setEmail(c+"@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(cst->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setCustomer(cst);
                currentAccount.setStatusAccount(StatusAccount.CREATED);
                currentAccount.setOverDraft(9000);

                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatusAccount(StatusAccount.CREATED);
                savingAccount.setInterestRate(5.5);
                savingAccount.setCustomer(cst);
                bankAccountRepository.save(savingAccount);
                });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAmount(Math.random() * 9000);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setBankAccount(acc);
                    accountOperation.setOperationType(Math.random()> 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    accountOperationRepository.save(accountOperation);
                }
            });


        };

    }

}
