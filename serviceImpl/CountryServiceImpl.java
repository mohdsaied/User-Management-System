package com.usm.serviceImpl;

import com.usm.entity.Country;
import com.usm.repository.CountryRepository;
import com.usm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public String AddCountry(@RequestBody Country country) {
        Country save = countryRepository.save(country);
        return "saved.....";
    }
}
