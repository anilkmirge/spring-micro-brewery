package next.monster.springmicrobrewery.web.mappers;

import next.monster.springmicrobrewery.domain.Customer;
import next.monster.springmicrobrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

  CustomerDto customerToCustomerDto(Customer customer);
  Customer customerDtoToCustomer(CustomerDto dto);

}
