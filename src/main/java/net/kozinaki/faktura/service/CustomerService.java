package net.kozinaki.faktura.service;

import java.time.LocalDateTime;
import java.util.List;
import net.kozinaki.faktura.domain.entities.Customer;
import net.kozinaki.faktura.domain.pojo.CustomerDto;
import net.kozinaki.faktura.domain.pojo.CustomerIdDto;
import net.kozinaki.faktura.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<CustomerDto> getCustomers() {
    List<Customer> customers = customerRepository.findAllByDeleteIsNull();
    return customers.stream().map(CustomerDto::new).toList();
  }

  public Long createCustomer(CustomerDto customerDto) {
    Customer customer = customerDto.toCustomer();
    Customer resultCustomer = customerRepository.save(customer);
    return resultCustomer.getId();
  }

  @Transactional
  public Long deleteCustomer(CustomerIdDto customerIdDto) {
    customerRepository.setDeleteByCustomerId(LocalDateTime.now(), customerIdDto.id);
    return customerIdDto.id;
  }

}
