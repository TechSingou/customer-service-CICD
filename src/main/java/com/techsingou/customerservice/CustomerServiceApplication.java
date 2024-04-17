package com.techsingou.customerservice;

import com.techsingou.customerservice.entity.Customer;
import com.techsingou.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Singou")
                    .email("Singou@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Moh")
                    .email("moh@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("drissa")
                    .email("drissa@gmail.com")
                    .build());
        };
    }
}
