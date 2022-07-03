package io.thinkingcode.msscbrewery.web.mappers;

import io.thinkingcode.msscbrewery.domain.Beer;
import io.thinkingcode.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
