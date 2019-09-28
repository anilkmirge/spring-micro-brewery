package next.monster.springmicrobrewery.services;

import lombok.extern.slf4j.Slf4j;
import next.monster.springmicrobrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
  @Override
  public BeerDto getBeerById(UUID beerId) {
    return BeerDto.builder().id(UUID.randomUUID())
            .beerName("Galaxy Cat")
            .beerStyle("Pale Ale")
            .upc(28937183921739L)
            .build();
  }

  @Override
  public BeerDto saveNewBeer(BeerDto beerDto) {
    return BeerDto.builder()
            .id(UUID.randomUUID())
            .build();
  }

  @Override
  public void updateBeer(UUID beerId, BeerDto beerDto) {
    // todo impl - would add a real impl to update beer
    log.debug("Updating a beer with id ", beerId);
  }

  @Override
  public void deleteById(UUID beerId) {
    log.debug("Deleting a beer with Id ", beerId);
  }
}
