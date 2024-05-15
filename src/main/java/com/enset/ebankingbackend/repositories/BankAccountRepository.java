package com.enset.ebankingbackend.repositories;

import com.enset.ebankingbackend.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
