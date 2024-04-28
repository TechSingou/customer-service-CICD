package com.techsingou.customerservice.web;

import com.techsingou.customerservice.entity.Customer;
import com.techsingou.customerservice.mapper.CustomerMapper;
import com.techsingou.customerservice.repository.CustomerRepository;
import com.techsingou.customerservice.stub.CustomerServiceGrpc;
import com.techsingou.customerservice.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerGrpcService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetAllCustomersResponse> responseObserver) {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers = customerList.stream().map(customerMapper::fromEntityCustomer).toList();
        CustomerServiceOuterClass.GetAllCustomersResponse allCustomersResponse = CustomerServiceOuterClass.GetAllCustomersResponse.newBuilder()
                .addAllCustomers(grpcCustomers)
                .build();
        responseObserver.onNext(allCustomersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customerEntity = customerRepository.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.GetCustomerByIdResponse customerByIdResponse = CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                .setCustomer(customerMapper.fromEntityCustomer(customerEntity))
                .build();
        responseObserver.onNext(customerByIdResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        Customer customerEntity = customerMapper.fromGrpcCustomer(request.getNewCustomerDTO());
        Customer savedCustomer = customerRepository.save(customerEntity);
        CustomerServiceOuterClass.SaveCustomerResponse saveCustomerResponse = CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                .setSavedCustomer(customerMapper.fromEntityCustomer(savedCustomer))
                .build();
        responseObserver.onNext(saveCustomerResponse);
        responseObserver.onCompleted();
    }
}
