package com.hechizos.culinarios.Services.Impl;

import org.springframework.stereotype.Service;

import com.hechizos.culinarios.Models.Country;
import com.hechizos.culinarios.Repositories.CountryRepository;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Services.CountryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryImpl extends CrudImpl<Country, Long> implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    protected GenericRepository<Country, Long> getRepository() {
        return countryRepository;
    }

    @Override
    public Country findByIdCountry(Long idCountry) {
        return countryRepository.findByIdCountry(idCountry);
    }

}
