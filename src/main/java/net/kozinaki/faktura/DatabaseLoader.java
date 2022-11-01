package net.kozinaki.faktura;

import net.kozinaki.faktura.domain.entities.Customer;
import net.kozinaki.faktura.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  private final CustomerRepository repository;

  @Autowired
  public DatabaseLoader(CustomerRepository repository) {
    this.repository = repository;
  }

  private String phone;
  private String address;
  private String email;
  private String inn;
  private String description;

  @Override
  public void run(String... args) throws Exception {
    this.repository.save(
        new Customer(
            "Frodo Baggins",
            "+7987654321",
            "Russia, Moscow, Kalinina d. 1, kv. 2", "nevermore@mail.ru",
            "1234567890",
            "Hello World!"
            ));
  }
}
