package com.example.confrence.service.impl;

import com.example.confrence.domain.Formula;
import com.example.confrence.repository.FormulaRepository;
import com.example.confrence.service.FormulaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaImpl implements FormulaService {

    private final FormulaRepository formulaRepository;

    public FormulaImpl(FormulaRepository formulaRepository) {
        this.formulaRepository = formulaRepository;
    }

    @Override
    public List<Formula> findAllFormulas() {
        return formulaRepository.findAll();
    }

    @Override
    public void addFormula(Formula formula) {
        formulaRepository.save(formula);
    }

}
