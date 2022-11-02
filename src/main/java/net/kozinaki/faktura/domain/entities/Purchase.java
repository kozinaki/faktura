package net.kozinaki.faktura.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Purchase extends Common {

  private @Id
  @GeneratedValue Long id;
  @ManyToOne
  private Customer customer;
  @ManyToOne
  private Wielder wielder;
  private BigDecimal sum;
  @ManyToOne
  private Discount discount;
  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  private List<Payment> payments;
  private BigDecimal remains;
  private BigDecimal margin;
  private BigDecimal delivery;
  private BigDecimal rollback;
  private BigDecimal installation;
  private LocalDate orderCompleteDate;
  private String note;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getSum() {
    return sum;
  }

  public void setSum(BigDecimal sum) {
    this.sum = sum;
  }

  public Discount getDiscount() {
    return discount;
  }

  public void setDiscount(Discount discount) {
    this.discount = discount;
  }

  public List<Payment> getPrepayments() {
    return payments;
  }

  public void setPrepayments(List<Payment> payments) {
    this.payments = payments;
  }
}
