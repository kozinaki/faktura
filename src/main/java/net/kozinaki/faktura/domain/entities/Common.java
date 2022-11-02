package net.kozinaki.faktura.domain.entities;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class Common {

  protected LocalDateTime create;
  protected LocalDateTime update;
  protected LocalDateTime delete;

  public LocalDateTime getCreate() {
    return create;
  }

  public void setCreate(LocalDateTime create) {
    this.create = create;
  }

  public LocalDateTime getUpdate() {
    return update;
  }

  public void setUpdate(LocalDateTime update) {
    this.update = update;
  }

  public LocalDateTime getDelete() {
    return delete;
  }

  public void setDelete(LocalDateTime delete) {
    this.delete = delete;
  }

  @PrePersist
  public void setCreateAndUpdateDate() {
    LocalDateTime now = LocalDateTime.now();
    setCreate(now);
    setUpdate(now);
  }

  @PreUpdate
  public void setUpdateDate() {
    LocalDateTime now = LocalDateTime.now();
    setUpdate(now);
  }
}
