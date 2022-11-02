package net.kozinaki.faktura.domain.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.kozinaki.faktura.domain.entities.Customer;
import net.kozinaki.faktura.domain.entities.Purchase;

public class CustomerDto {
  public final Long id;
  public final String name;
  public final String phone;
  public final String address;
  public final String email;
  public final String inn;
  public final String description;
  private final List<Purchase> purchases;

  public CustomerDto(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.phone = customer.getPhone();
    this.address = customer.getAddress();
    this.email = customer.getEmail();
    this.inn = customer.getInn();
    this.description = customer.getDescription();
    this.purchases = customer.getPurchases();
  }

  @JsonCreator
  public CustomerDto(
      @JsonProperty("id") Long id,
      @JsonProperty("name") String name,
      @JsonProperty("phone") String phone,
      @JsonProperty("address") String address,
      @JsonProperty("email") String email,
      @JsonProperty("inn") String inn,
      @JsonProperty("description") String description) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.email = email;
    this.inn = inn;
    this.description = description;
    this.purchases = new ArrayList<>();
  }

  public Customer toCustomer() {
    return new Customer(
        name,
        phone,
        address,
        email,
        inn,
        description
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDto employee = (CustomerDto) o;
    return Objects.equals(id, employee.id) &&
        Objects.equals(name, employee.name) &&
        Objects.equals(description, employee.description) &&
        Objects.equals(inn, employee.inn) &&
        Objects.equals(phone, employee.phone) &&
        Objects.equals(address, employee.address) &&
        Objects.equals(email, employee.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, inn, phone, address, email);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", inn='" + inn + '\'' +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
