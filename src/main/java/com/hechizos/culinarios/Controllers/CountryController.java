package com.hechizos.culinarios.Controllers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hechizos.culinarios.Dto.CountryDto;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.Country;
import com.hechizos.culinarios.Services.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<CountryDto>> readAll() throws Exception {
        List<CountryDto> list = countryService.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    private CountryDto convertToDto(Country obj) {
        return modelMapper.map(obj, CountryDto.class);
    }

}
