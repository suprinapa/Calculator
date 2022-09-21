package com.example.confrence.controller;

import com.example.confrence.domain.Formula;
import com.example.confrence.dto.FormulaResponseData;
import com.example.confrence.service.FormulaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class FormulaController {
    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @GetMapping("formula")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    FormulaResponseData getAllData(@ModelAttribute("formula") Formula formula) {
        FormulaResponseData formulaResponseData = new FormulaResponseData();
        formulaResponseData.setData(formulaService.findAllFormulas());
        return formulaResponseData;
    }

    @PostMapping("formula/addFormula")
    public String addFormula(Formula formula){
        formulaService.addFormula(formula);
        return ("addFormula");
    }
    }
