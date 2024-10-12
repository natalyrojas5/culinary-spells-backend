package com.hechizos.culinarios.Services;

import com.hechizos.culinarios.Models.Country;

public interface CountryService extends ICrud<Country, Long> {
    Country findByIdCountry(Long idCountry);
}
