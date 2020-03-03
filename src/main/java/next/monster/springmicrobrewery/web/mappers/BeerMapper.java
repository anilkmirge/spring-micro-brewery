package next.monster.springmicrobrewery.web.mappers;


import next.monster.springmicrobrewery.domain.Beer;
import next.monster.springmicrobrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

  BeerDto beerToBeerDto(Beer beer);

  Beer beerDtoToBeer(BeerDto dto);

}
