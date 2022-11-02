package net.kozinaki.faktura.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Discount {

  private @Id
  @GeneratedValue Long id;
  private BigDecimal amount;
  private BigDecimal percent;
  @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
  private List<Purchase> purchases;
}
