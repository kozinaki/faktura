package net.kozinaki.faktura.repository;

import java.time.LocalDateTime;
import java.util.List;
import net.kozinaki.faktura.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findAllByDeleteIsNull();

  @Modifying
  @Query("update Customer c set c.delete = ?1 where c.id = ?2")
  int setDeleteByCustomerId(LocalDateTime delete, Long id);

  @Modifying
  @Query("update Customer c "
      + "set c.name = ?1, "
          + "c.phone = ?2, "
          + "c.address = ?3, "
          + "c.email = ?4, "
          + "c.inn = ?5, "
          + "c.description = ?6 "
      + "where c.id = ?7")
  int updateByCustomerId(
      String name,
      String phone,
      String address,
      String email,
      String inn,
      String description,
      Long id
  );

}
