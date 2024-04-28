package com.techsingou.customerservice.mapper;

import com.techsingou.customerservice.dto.CustomerRequest;
import com.techsingou.customerservice.entity.Customer;
import com.techsingou.customerservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper mapper = new ModelMapper();

    public Customer from(CustomerRequest customerRequest) {
        /* Customer customer = new Customer();
        customer.setName(customerRequest.name());
        customer.setEmail(customerRequest.email());*/
        return mapper.map(customerRequest, Customer.class);
    }

    public CustomerServiceOuterClass.Customer fromEntityCustomer(Customer customerEntity) {
        return mapper.map(customerEntity, CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer fromGrpcCustomer(CustomerServiceOuterClass.NewCustomerDTO newCustomerGrpcDTO) {
        return mapper.map(newCustomerGrpcDTO, Customer.class);
    }
}
