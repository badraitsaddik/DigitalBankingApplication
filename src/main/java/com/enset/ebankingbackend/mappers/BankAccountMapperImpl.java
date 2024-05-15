package com.enset.ebankingbackend.mappers;


import com.enset.ebankingbackend.Entities.AccountOperation;
import com.enset.ebankingbackend.Entities.CurrentAccount;
import com.enset.ebankingbackend.Entities.Customer;
import com.enset.ebankingbackend.Entities.SavingAccount;
import com.enset.ebankingbackend.dtos.AccountOperationDTO;
import com.enset.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.enset.ebankingbackend.dtos.CustomerDTO;
import com.enset.ebankingbackend.dtos.SavingBankAccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        //customerDTO.setId( customer.getId() );
        //customerDTO.setName( customer.getName() );
        //customerDTO.setEmail( customer.getEmail() );

        return customerDTO;
    }
    public Customer toCustomer(CustomerDTO customerDTO) {


        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        //customer.setId( customerDTO.getId() );
        //customer.setName( customerDTO.getName() );
        //customer.setEmail( customerDTO.getEmail() );

        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(toCustomer(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(toCustomer(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}
