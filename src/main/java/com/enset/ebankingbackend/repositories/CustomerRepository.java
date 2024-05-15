package com.enset.ebankingbackend.repositories;

import com.enset.ebankingbackend.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.name like :keyword")
    List<Customer> searchCustomer(@RequestParam("keyword") String keyword);
}
