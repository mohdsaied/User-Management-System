package com.usm.controller;

import com.usm.entity.Country;
import com.usm.repository.CountryRepository;
import com.usm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @PostMapping("/{addCountry}")
    public ResponseEntity<String> addCountry(@RequestBody Country country){
        String string = countryService.AddCountry(country);
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
}
