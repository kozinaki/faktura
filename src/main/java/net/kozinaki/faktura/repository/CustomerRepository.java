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
  void setDeleteByCustomerId(LocalDateTime delete, Long id);

}
