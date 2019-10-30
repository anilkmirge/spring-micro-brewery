package next.monster.springmicrobrewery.services;

import lombok.extern.slf4j.Slf4j;
import next.monster.springmicrobrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
  @Override
  public CustomerDto getCustomerById(UUID id) {
    return CustomerDto.builder().id(UUID.randomUUID())
              .customerName("Anil Mirge").build();
  }

  @Override
  public CustomerDto saveNewCustomer(CustomerDto customer) {
    return CustomerDto.builder()
            .id(UUID.randomUUID())
            .customerName(customer.getCustomerName())
            .build();
  }

  @Override
  public void updateCustomer(UUID customerId, CustomerDto customer) {
    log.debug("Updating a customer with Id ", customerId);
  }

  @Override
  public void deleteById(UUID customerId) {
    log.debug("Deleting a customer by Id ", customerId);
  }
}
