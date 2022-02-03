package com.justin;

import com.justin.config.SpringdataConfig;
import com.justin.pojo.Customer;
import com.justin.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Optional;

//@ContextConfiguration("/Spring.xml")
@ContextConfiguration(classes = SpringdataConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaTest {

    @Resource
    CustomerRepository customerRepository;

    @Test
    public void testR(){
        Optional<Customer> customerById = customerRepository.findById(1L);
        Customer customer = customerById.orElseGet(null);
        System.out.println(customer);
    }

    @Test
    public void testC(){
        Customer cust = new Customer("Gary", "Hospital");
        customerRepository.save(cust);

        customerRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testU(){
        Customer customer = customerRepository.findById(2L).orElseGet(null);
        System.out.println(customer);
        customer.setCustAddress("Hotel");
        customerRepository.save(customer);
    }

}
