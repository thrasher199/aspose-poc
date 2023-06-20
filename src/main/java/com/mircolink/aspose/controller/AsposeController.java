package com.mircolink.aspose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mircolink.aspose.CustomerDTO;
import com.mircolink.aspose.model.Customer;
import com.mircolink.aspose.model.CustomerLoan;
import com.mircolink.aspose.repositories.CustomerRepository;
import com.mircolink.aspose.service.MyReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AsposeController {

    private final ObjectMapper objectMapper;
    private final MyReportService myReportService;
    private final CustomerRepository customerRepository;
    public AsposeController(ObjectMapper objectMapper, MyReportService myReportService, CustomerRepository customerRepository) {
        this.objectMapper = objectMapper;
        this.myReportService = myReportService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/generateDocument")
    public ResponseEntity<List<CustomerDTO>> generateReport() throws Exception {
        generateDummyData();
        myReportService.generateReport(1);
        List<CustomerDTO> customers = customerRepository.findAll()
                .stream().map(x -> objectMapper.convertValue(x, CustomerDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    private void generateDummyData(){
        int i = 1;
        Customer customer = new Customer();
        customer.setId((long) i);
        customer.setCustomerName("Abu" + i);
        customer.setAddress("Abu Gracia" + i);

        CustomerLoan customerLoanHP = new CustomerLoan();
        customerLoanHP.setLoanType("HR" + i);
        customerLoanHP.setLoanAmount(new BigDecimal(100000).add(BigDecimal.valueOf(i)));
        customerLoanHP.setCustomer(customer);

        CustomerLoan customerLoanM = new CustomerLoan();
        customerLoanM.setLoanType("Mortage" + i);
        customerLoanM.setLoanAmount(new BigDecimal(1000000).add(BigDecimal.valueOf(i)));
        customerLoanM.setCustomer(customer);

        CustomerLoan customerLoanP = new CustomerLoan();
        customerLoanP.setLoanType("Personal" + i);
        customerLoanP.setLoanAmount(new BigDecimal(10000).add(BigDecimal.valueOf(i)));
        customerLoanP.setCustomer(customer);

        Set<CustomerLoan> customerLoanSet = new LinkedHashSet<>();
        customerLoanSet.add(customerLoanHP);
        customerLoanSet.add(customerLoanM);
        customerLoanSet.add(customerLoanP);

        customer.setCustomerLoans(customerLoanSet);
        customerRepository.save(customer);
    }
}

