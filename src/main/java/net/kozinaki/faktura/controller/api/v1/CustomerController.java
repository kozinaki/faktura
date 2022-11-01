package net.kozinaki.faktura.controller.api.v1;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import java.util.List;
import net.kozinaki.faktura.domain.pojo.CustomerDto;
import net.kozinaki.faktura.domain.pojo.CustomerIdDto;
import net.kozinaki.faktura.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CustomerDto>> getCustomers() {
    return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerIdDto> createCustomer(@RequestBody CustomerDto customerDto) {
    Long id = customerService.createCustomer(customerDto);
    return new ResponseEntity<>(new CustomerIdDto(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerIdDto> deleteCustomer(@RequestBody CustomerIdDto customerIdDto) {
    Long id = customerService.deleteCustomer(customerIdDto);
    return new ResponseEntity<>(new CustomerIdDto(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/schema", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getCustomerSchema() throws JsonMappingException {
    SchemaFactoryWrapper personVisitor = new HyperSchemaFactoryWrapper();
    ObjectMapper mapper = new ObjectMapper();
    mapper.acceptJsonFormatVisitor(CustomerDto.class, personVisitor);
    ObjectSchema customerSchema = personVisitor.finalSchema().asObjectSchema();
    String properties = customerSchema
        .getProperties()
        .keySet()
        .stream()
        .map(attribute -> "\"".concat(attribute).concat("\""))
        .reduce((att1, att2) -> att1.concat(",").concat(att2))
        .orElse("");
    return "{\"properties\":[".concat(properties).concat("]}");
  }

}