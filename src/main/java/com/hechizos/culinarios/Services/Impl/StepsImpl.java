package com.hechizos.culinarios.Services.Impl;

import org.springframework.stereotype.Service;

import com.hechizos.culinarios.Models.Steps;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.StepsRepository;
import com.hechizos.culinarios.Services.StepsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StepsImpl extends CrudImpl<Steps, Long> implements StepsService {

    private final StepsRepository stepsRepository;

    @Override
    protected GenericRepository<Steps, Long> getRepository() {
        return stepsRepository;
    }
}
