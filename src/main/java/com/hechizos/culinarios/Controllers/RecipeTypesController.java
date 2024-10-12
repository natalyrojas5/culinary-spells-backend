package com.hechizos.culinarios.Controllers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hechizos.culinarios.Dto.RecipeTypesDto;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.RecipeTypes;
import com.hechizos.culinarios.Services.RecipeTypesService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipetypes")
public class RecipeTypesController {

    private final RecipeTypesService recipeTypesService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<RecipeTypesDto>> readAll() throws Exception {
        List<RecipeTypesDto> list = recipeTypesService.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    private RecipeTypesDto convertToDto(RecipeTypes obj) {
        return modelMapper.map(obj, RecipeTypesDto.class);
    }

}
