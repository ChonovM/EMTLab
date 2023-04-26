package com.example.emtlab.service;

import com.example.emtlab.model.Country;
import com.example.emtlab.repositories.CountryRepository;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Country with id " + id + " not found."));
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country editCountry(Long id, Country country) {
        Country existingCountry = getCountryById(id);
        existingCountry.setName(country.getName());
        existingCountry.setContinent(country.getContinent());
        return countryRepository.save(existingCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}