package com.techsingou.customerservice.web;

import com.techsingou.customerservice.dto.CustomerRequest;
import com.techsingou.customerservice.entity.Customer;
import com.techsingou.customerservice.mapper.CustomerMapper;
import com.techsingou.customerservice.repository.CustomerRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {

    private CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @WebMethod(operationName = "listCustomers")
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @WebMethod
    public Customer customerById(@WebParam(name = "id") Long id) {
        return customerRepository.findById(id).get();
    }

    @WebMethod
    public Customer saveCustomer(@WebParam(name = "customer") CustomerRequest customerRequest) {
        Customer customer = customerMapper.from(customerRequest);
        return customerRepository.save(customer);
    }
}
