package com.example.confrence.controller;

import com.example.confrence.domain.Formula;
import com.example.confrence.service.FormulaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FormulaController {
    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @GetMapping("formula")
    public ModelAndView getAllData(@ModelAttribute("formula") Formula formula) {
        ModelAndView modelAndView = new ModelAndView("calculator");
        List<Formula> allFormulasList = formulaService.findAllFormulas();
        modelAndView.addObject("allFormulaList", allFormulasList);
        return modelAndView;
    }
    }
