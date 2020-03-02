package next.monster.springmicrobrewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import next.monster.springmicrobrewery.services.CustomerService;
import next.monster.springmicrobrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
    return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDto> handlePost(@Valid @RequestBody CustomerDto customer) {
    CustomerDto savedCustomer = customerService.saveNewCustomer(customer);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void handleUpdate(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customer) {
    customerService.updateCustomer(customerId, customer);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomerById(@PathVariable("customerId") UUID customerId) {
    customerService.deleteById(customerId);
  }
}

