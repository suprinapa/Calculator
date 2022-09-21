package com.example.confrence.service;

import com.example.confrence.domain.Formula;

import java.util.List;


public interface FormulaService {

List<Formula> findAllFormulas();
void addFormula(Formula formula);
}

