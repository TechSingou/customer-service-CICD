package com.techsingou.customerservice.mapper;

import com.techsingou.customerservice.dto.CustomerRequest;
import com.techsingou.customerservice.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper mapper =new ModelMapper();
    public Customer from(CustomerRequest customerRequest){
        /* Customer customer = new Customer();
        customer.setName(customerRequest.name());
        customer.setEmail(customerRequest.email());*/
        return mapper.map(customerRequest, Customer.class);
    }
}
