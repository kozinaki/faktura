package net.kozinaki.faktura.domain.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Common {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String phone;
  private String address;
  private String email;
  private String inn;
  private String description;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<Purchase> purchases;

  public Customer() {
  }

  public Customer(String name, String phone, String address, String email, String inn, String description) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.email = email;
    this.inn = inn;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInn() {
    return inn;
  }

  public void setInn(String inn) {
    this.inn = inn;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Purchase> getPurchases() {
    return this.purchases;
  }

  public void setPurchases(List<Purchase> purchases) {
    this.purchases = purchases;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer employee = (Customer) o;
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
