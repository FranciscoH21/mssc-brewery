package io.thinkingcode.msscbrewery.services;

import io.thinkingcode.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);
}
