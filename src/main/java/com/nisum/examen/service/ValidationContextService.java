package com.nisum.examen.service;

import org.springframework.stereotype.Service;

import com.nisum.examen.validation.ValidationStrategy;

import java.util.Map;

@Service
public class ValidationContextService {

    private final Map<String, ValidationStrategy> strategies;

    public ValidationContextService(Map<String, ValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    public boolean validate(String type, String input) {
        ValidationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No se encontró estrategia para: " + type);
        }
        return strategy.isValid(input);
    }
}