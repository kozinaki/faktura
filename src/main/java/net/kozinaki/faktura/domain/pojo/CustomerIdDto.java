package net.kozinaki.faktura.domain.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerIdDto {

  public final Long id;

  @JsonCreator
  public CustomerIdDto(@JsonProperty("id") Long id) {
    this.id = id;
  }

}
