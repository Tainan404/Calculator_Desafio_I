package br.feevale.calculator;

/*
 * Evaluation Postfix Expression
 * for each element in postfix expression do
 *   if operand is encountered
 *       push it onto stack
 *   else if operator is encountered, pop 2 elements
 *       A = top element
 *       B = next top element
 *       result = B operator A
 *       push result onto stack
 * return element of stack top
 * */

import java.util.ArrayList;
import java.util.Stack;

public class EvaluateString {
    private double outPut;

    public EvaluateString(ArrayList<String> expression) {
        Stack<String> stack = new Stack<>();

        for (String el : expression) {
            if (!isOperator(el)) {
                stack.push(el);
            } else {
                String A = stack.pop();
                String B = stack.pop();
                stack.push(Double.toString(resolveEquation(A, B, el)));
            }
        }

        setOutPut(Double.parseDouble(stack.pop()));
    }

    private boolean isOperator(String operator) {
        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    private double resolveEquation(String operandA, String operandB, String operator) {
        double A = Double.parseDouble(operandA);
        double B = Double.parseDouble(operandB);
        double result;

        switch (operator) {
            case "+":
                result = B + A;
                break;
            case "-":
                result = B - A;
                break;
            case "*":
                result = B * A;
                break;
            case "/":
                result = B / A;
                break;
            default:
                return 0;
        }

        return  result;
    }

    public double getOutPut() {
        return outPut;
    }

    public void setOutPut(double outPut) {
        this.outPut = outPut;
    }
}
