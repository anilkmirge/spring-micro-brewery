package next.monster.springmicrobrewery.services;

import next.monster.springmicrobrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
  public CustomerDto getCustomerById(UUID id);

  public CustomerDto saveNewCustomer(CustomerDto customer);

  void updateCustomer(UUID customerId, CustomerDto customer);

  void deleteById(UUID customerId);
}
