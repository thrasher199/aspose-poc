package com.mircolink.aspose.service;

import com.aspose.words.net.System.Data.DataTable;
import com.mircolink.aspose.dao.MyDao;
import com.mircolink.aspose.model.Customer;
import com.mircolink.aspose.model.CustomerLoan;
import com.mircolink.aspose.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class MyReportServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MyReportService reportService;

    @Autowired
    private MyDao myDao;


    void initData(int i) {
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

    private void initCustomer(int i){
        Customer customer = new Customer();
        customer.setId((long) i);
        customer.setCustomerName("Customer " + i);
        customer.setAddress("Customer " + i + " Address");
        customer.setCustomerLoans(init1000LoanType(90, customer));
        customerRepository.save(customer);
    }

    private Set<CustomerLoan> init1000LoanType(int size, Customer customer){
        Set<CustomerLoan> customerLoanSet = new LinkedHashSet<>();
        for (int i = 1; i <= size ; i++) {
            CustomerLoan customerLoan = new CustomerLoan();
            customerLoan.setLoanType("LT-" + i);
            customerLoan.setLoanAmount(new BigDecimal(100).add(BigDecimal.valueOf(i)));
            customerLoan.setCustomer(customer);
            customerLoanSet.add(customerLoan);
        }

        return customerLoanSet;
    }

    @Test
    void generateReport() throws Exception {
        int size = 10000;
        for (int i = 1; i <= size; i++) {
            //initData(i);
            initCustomer(i);
        }
        //done insert data in db
        System.out.println("Done insert");
        DataTable dataTable = new DataTable();
        List<Customer> customerList = customerRepository.findAll();

        long start = System.currentTimeMillis();
        for (int i = 1; i <= size; i++) {
            reportService.generateReport(i);
        }

        long end = System.currentTimeMillis();
        long execution  = end - start;
        System.out.println("execution in " + execution  + " millisecond");

    }
}