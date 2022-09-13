package com.example.confrence.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class Calculator {

    public String calcMain(String input){
        System.out.println("Enter a formula: ");
        String inputFormula = "a+b*c";
        String[] formulaArray = inputFormula.split("(?<=[-+/^*!()])|(?=[-+/^*!()])");
        System.out.println(Arrays.toString(formulaArray));

        String[] outputArray = input.split("(?<=[-+/^*!()])|(?=[-+/^*!()])");
        System.out.println(Arrays.toString(outputArray));
        Stack<String> operatorStack = new Stack<>();
        Stack<String> valueStack = new Stack<>();
        for (int i = 0; i < outputArray.length; i++) {
            if (isOperator(outputArray[i])) {
                if (operatorStack.isEmpty() || checkPrecedence(outputArray[i]) > checkPrecedence(operatorStack.peek())) {
                    operatorStack.push(outputArray[i]);
                    System.out.println(operatorStack);
                    System.out.println(valueStack);
                } else {
                    double value = 0;
                    while (!operatorStack.empty() && checkPrecedence(outputArray[i]) <= checkPrecedence(operatorStack.peek())) {
                        String operator = operatorStack.pop();
                        String popValue1 = "0.0";
                        if(!operator.equals("!")){
                             popValue1 = valueStack.pop();
                        }
                        String popValue2 = valueStack.pop();
                        value = performProcess(operator, Double.parseDouble(popValue2), Double.parseDouble(popValue1));
                        valueStack.push(String.valueOf(value));

//                        if (operatorStack.peek().equals("!")){
//                            String top = operatorStack.pop();
//                            String popValue2 = valueStack.pop();
//                            value = performProcess(top, Double.parseDouble(popValue2), 0.0);
//                            valueStack.push(String.valueOf(value));
//                            System.out.println(operatorStack);
//                            System.out.println(valueStack);
//                        }else {
//                            String top = operatorStack.pop();
//                            String popValue1 = valueStack.pop();
//                            String popValue2 = valueStack.pop();
//                            value = performProcess(top, Double.parseDouble(popValue2), Double.parseDouble(popValue1));
//                            valueStack.push(String.valueOf(value));
//                            System.out.println(operatorStack);
//                            System.out.println(valueStack);
//                        }
                    }
                    operatorStack.push(outputArray[i]);
                }
            } else {
                valueStack.push(outputArray[i]);
            }
        }
        while (!operatorStack.empty()) {

            String top = operatorStack.pop();
            if (top.equals("!")){
                String popValue2 = valueStack.pop();
                valueStack.push(String.valueOf(performProcess(top, Double.parseDouble(popValue2), 0.0)));
            }else {
                String popValue1 = valueStack.pop();
                String popValue2 = valueStack.pop();
                valueStack.push(String.valueOf(performProcess(top, Double.parseDouble(popValue2), Double.parseDouble(popValue1))));
            }
        }
        System.out.println(valueStack);
        System.out.println(operatorStack);
        return valueStack.peek();
    }
    public static Map<String, Integer> FormulaMap() {
        List<String> operatorArray = new ArrayList<>();
        List<String> variableArray = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a formula: ");
        String inputFormula = scanner.next();

        String[] formulaArray = inputFormula.split("(?<=[-+/^*!()])|(?=[-+/^*!()])");
        System.out.println(Arrays.toString(formulaArray));

        for (String formula : formulaArray) {
            if (isOperator(formula))  operatorArray.add(formula);
            else variableArray.add(formula);
        }
        Map<String, Integer> map = new HashMap<>();

        for (String variable: variableArray) {
            System.out.println(variable + ": ");
            map.put(variable, scanner.nextInt());
        }
      return map;
    }
    public static double performProcess(String operator, double number1, double number2) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            case "^":
                return Math.pow(number1, number2);
            case "!":
                return factorialHavingLargeResult(number1).doubleValue();
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }

    public static BigInteger factorialHavingLargeResult(double n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static int checkPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 5;
        }
    }

    public static Boolean isOperator(String operator) {
        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
            case "!":
                return true;
        }
        return false;
    }
}
