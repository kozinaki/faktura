package net.kozinaki.faktura.domain.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Payment extends Common {

  private @Id
  @GeneratedValue Long id;
  private BigDecimal amount;
  @ManyToOne
  private Purchase purchase;
}
