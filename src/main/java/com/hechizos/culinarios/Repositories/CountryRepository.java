package com.hechizos.culinarios.Repositories;

import com.hechizos.culinarios.Models.Country;

public interface CountryRepository extends GenericRepository<Country, Long> {
    Country findByIdCountry(Long idCountry);
}
