package com.example.confrence.controller;

import com.example.confrence.domain.History;
import com.example.confrence.domain.MathExpression;
import com.example.confrence.dto.FormulaResponseData;
import com.example.confrence.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.confrence.service.Calculator;

@Controller
public class CalculatorController {

    @Autowired
    FormulaResponseData formulaResponseData;

    @Autowired
    Calculator calculatorService;

    @Autowired
    HistoryRepository historyRepository;

    @PostMapping("calculator")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MathExpression addCalculation(@ModelAttribute("calc") MathExpression mathExpression) {
        if (!mathExpression.getInputExpression().equals("")) {
            History history = new History();
            String inputExpression = mathExpression.getInputExpression();
            String output = calculatorService.calcMain(inputExpression);
            history.setInputExpression(inputExpression);
            history.setOutputExpression(output);
            historyRepository.save(history);
            mathExpression.setInputExpression(output);
            return mathExpression;
        }
        return new MathExpression();
    }

//    @PostMapping("calculator")
//    public String addCalculation(@ModelAttribute("calc") MathExpression mathExpression){
////        System.out.println("Expression = "+ mathExpression.getInputExpression());
//        String inputExpression = mathExpression.getInputExpression();
//        mathExpression.setInputExpression(inputExpression);
//        return "calculator";
//    }
    @GetMapping("calculator")
    public String calculation(@ModelAttribute("calc")MathExpression mathExpression){
        return "calculator";
    }
}
