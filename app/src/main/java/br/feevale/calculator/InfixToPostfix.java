package br.feevale.calculator;

import java.util.ArrayList;
import java.util.Stack;

/*
 * Rules:
 * - If stack is empty or contains a left parenthesis on top, push the incoming operator onto the stack.
 * - If incoming symbol is '(', push it onto stack.
 * - If incoming symbol is ')', pop the stack and print the operators until left parenthesis is found.
 * - If incoming symbol has higher precedence than the top of the stack, push it on the stack.
 * - If incoming symbol has lower precedence than the top of the stack, pop and print the top.
 * Then test the incoming operator against the new top of the stack.
 * - If incoming symbol has same precedence with the top of the stack, use associativity rule.
 * - At the end of the expression, pop and print all operators of stack.
 * Associativity Rule:
 * - Left to Right(+ and -, * and /): pop and print the top of the stack and then push the incoming operator.
 * - Right to Left(^): push the incoming operator to the stack
 * */

public class InfixToPostfix {

    private ArrayList<String> outPut;

    public InfixToPostfix(String[] exp) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> result = new ArrayList<>();

        for (String s : exp) {
            if (!isOperator(s)) {
                result.add(s);
            } else {
                while (!stack.empty() && (hasLowerPrecedence(stack.peek(), s) || hasSamePrecedence(stack.peek(), s))) {
                    result.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }

        setOutPut(result);
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

    private int precedenceLevel(String operator) {
        switch (operator) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    private boolean hasLowerPrecedence(String stackOp, String operator) {
        return precedenceLevel(operator) < precedenceLevel(stackOp);
    }

    private boolean hasSamePrecedence(String stackOp, String operator) {
        return precedenceLevel(operator) == precedenceLevel(stackOp);
    }

    public ArrayList<String> getOutPut() {
        return outPut;
    }

    public void setOutPut(ArrayList<String> outPut) {
        this.outPut = outPut;
    }
}
