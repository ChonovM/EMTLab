package com.example.emtlab.service;

import com.example.emtlab.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryById(Long id);
    Country addCountry(Country country);
    Country editCountry(Long id, Country country);
    void deleteCountry(Long id);
}