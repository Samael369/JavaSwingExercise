package part1.app;
import java.util.ArrayList;
import java.util.Stack;

public class Calc {
    public ArrayList<Object> operands = new ArrayList<>();
    private Stack<Character> operators = new Stack<>();
    private String expression;

    public Calc(String expression) {
        this.expression = expression;
    }

    public int checkPrecedence(char ch) {
        if (ch == '*' || ch == '/') {
            return 2;
        } else
            return 1;
    }

    public double calc() {
        Stack<Double> result = new Stack<>();
        for (int i = 0; i < operands.size(); i++) {
            System.out.println(operands.get(i));
            System.out.println(result);
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
            }
        }
        return result.peek();
    }

    public void interpretToShuntingYard() {
        ArrayList<Object> temp = castExpression();
        Double doubleType = 0.0;
        Character chartype = '(';
        for (Object object : temp) {
            if (object.getClass() == doubleType.getClass()) {
                double num = Double.parseDouble(object.toString());
                operands.add(num);
            } else if (object.getClass() == chartype.getClass()) {
                char ch = object.toString().charAt(0);
                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (operators.peek() != '(') {
                        operands.add(operators.pop());
                    }
                    operators.pop();
                } else if (!operators.empty() && (operators.peek() != '(' && operators.peek() != ')')
                        && (checkPrecedence(ch) <= checkPrecedence(operators.peek()))) {
                    operands.add(operators.pop());
                    operators.push(ch);
                } else {
                    operators.push(ch);
                }
            }
        }

        while (!operators.empty()) {
            operands.add(operators.pop());
        }
    }

    public ArrayList<Object> castExpression() {
        ArrayList<Object> temp = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                StringBuilder num = new StringBuilder();
                while ((ch >= '0' && ch <= '9') || ch == '.') {
                    num.append(ch);
                    if (i == expression.length() - 1)
                        break;
                    ch = expression.charAt(++i);
                }
                temp.add(Double.parseDouble(num.toString()));
            } else if (ch != ' ' || ch == ')') {
                temp.add(ch);
            }
        }
        return temp;
    }
}