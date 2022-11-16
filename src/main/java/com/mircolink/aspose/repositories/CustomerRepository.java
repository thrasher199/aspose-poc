package com.mircolink.aspose.repositories;

import com.mircolink.aspose.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}