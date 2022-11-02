package net.kozinaki.faktura.domain.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Wielder {

  private @Id @GeneratedValue Long id;
  private String firstName;
  private String lastName;
  private String description;
  private String role;
  private String position;
  @OneToMany(mappedBy = "wielder", fetch = FetchType.LAZY)
  private List<Purchase> purchases;

  private Wielder() {
  }

  public Wielder(
      String firstName,
      String lastName,
      String description,
      String role,
      String position
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
    this.role = role;
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wielder user = (Wielder) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(firstName, user.firstName) &&
        Objects.equals(lastName, user.lastName) &&
        Objects.equals(description, user.description) &&
        Objects.equals(role, user.role) &&
        Objects.equals(position, user.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, description, role, position);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", description='" + description + '\'' +
        ", role='" + role + '\'' +
        ", position='" + position + '\'' +
        '}';
  }
}