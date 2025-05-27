package part1.app;

import java.util.ArrayList;
import java.util.Stack;

public class Calc {
    public ArrayList<String> operands = new ArrayList<>();// output list to store operands and the final sequence for
                                                          // computation
    public Stack<String> operators = new Stack<>();// Stack for storing operands
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    // checking precedence of operators('*' & '/' higher than '+' & '-')
    public int checkPrecedence(String str) {
        if (str.equals("*") || str.equals("/")) {
            return 2;
        } else
            return 1;
    }

    // main method where calculation happens
    public String calc() {
        if (!correctParenthesis())// prints a message to the text field if parenthesis are used wrong
            return "Incorrect use of parenthesis";
        Stack<Double> result = new Stack<>();
        interpretToShuntingYard();// invoked to update operands and operators
        for (int i = 0; i < operands.size(); i++) {
            double second;
            double first;
            switch (operands.get(i).toString()) {
                case "+":
                    second = result.pop();
                    first = result.pop();
                    result.push(first + second);
                    break;
                case "-":
                    second = result.pop();
                    first = result.pop();
                    result.push(first - second);
                    break;
                case "*":
                    second = result.pop();
                    first = result.pop();
                    result.push(first * second);
                    break;
                case "/":
                    second = result.pop();
                    first = result.pop();
                    result.push(first / second);
                    break;
                default:
                    result.push(Double.parseDouble(operands.get(i).toString()));
                    break;
            }
        }
        return result.peek().toString();// returns the final value of expression
    }

    // modifies the expression to the right order of precendece
    // using shunting-yard algorithm
    public void interpretToShuntingYard() {
        ArrayList<String> temp = castExpression();
        for (String str : temp) {
            // adds str to operands if it's a number
            if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
                operands.add(str);
            } else if ("(".equals(str)) {
                operators.push(str);
                // pushes operators from stack to list until the peek of the stack is left
                // parenthesis
            } else if (")".equals(str)) {
                while (!"(".equals(operators.peek())) {
                    operands.add(operators.pop());
                }
                operators.pop();// discard the left parenthesis from stack
            }
            // pushes the operator at the top of the stack into the list and then pushes the
            // current operator into stack , if only the precedence of the operator at the
            // peek is higher
            else if (!operators.empty() && (!operators.peek().equals("(") && !operators.peek().equals(")"))
                    && (checkPrecedence(str) <= checkPrecedence(operators.peek()))) {
                operands.add(operators.pop());
                operators.push(str);
            } else {
                operators.push(str);
            }
        }

        // empty the stack into the output list
        while (!operators.empty()) {
            operands.add(operators.pop());
        }
    }

    // casts the expression into a secquence of strings to be easy-to-read
    public ArrayList<String> castExpression() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if ((ch >= '0' && ch <= '9') || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && ((ch >= '0' && ch <= '9') || ch == '.')) {
                    num.append(ch);
                    i++;
                    if (i < expression.length())
                        ch = expression.charAt(i);
                    else
                        break;
                }
                temp.add(num.toString());
                i--;
            } else if (ch != ' ') {
                temp.add(Character.toString(ch));
            }
        }
        return temp;
    }

    // checks if parenthesis are used right
    public boolean correctParenthesis() {
        ArrayList<String> temp = castExpression();
        Stack<String> parenthesis = new Stack<>();
        for (String str : temp) {
            if (str.equals("(")) {
                parenthesis.push(str);
            } else if (str.equals(")")) {
                if (parenthesis.empty()) {
                    return false;
                }
                parenthesis.pop();
            }
        }
        return parenthesis.empty();
    }
}