import jscheme.JS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Values values = new Values();
    public static void main(String[] args) throws IOException, ArithmeticException {
        int i = 1;
        String s = divideString(new BufferedReader(new InputStreamReader(System.in)).readLine());
        try {
            if(values.getNum1().matches("^[0-9]*$")){
                System.out.println(calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum2() + ")"));
            }
            else {
                numstoArabian();
                System.out.println(intToRoman(Integer.parseInt(calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum2() + ")"))));
                if (Integer.parseInt(calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum2() + ")")) < 0){
                    throw new ArithmeticException("В римской системе нет отрицательных чисел");
                }
            }
        }catch (NullPointerException e){
            System.out.println("Cтрока не является математической операцией - " + e);
        }
    }

    public static String divideString(String expression) {

        String operations = "[-,+/*]";
        Pattern pattern = Pattern.compile(operations);
        Matcher matcher = pattern.matcher(expression);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        String result = sb.toString();
        try {
            if (expression.contains("+") || expression.contains("-") || expression.contains("*") || expression.contains("/")) {
                int indexOfOperation = expression.indexOf(result);
                values.setNum1(expression.substring(0, indexOfOperation));
                int num1Length = String.valueOf(values.getNum1()).length();
                values.setOperation(expression.substring(num1Length, num1Length + 1));
                int num2Length = expression.length() - num1Length;
                values.setNum2(expression.substring(indexOfOperation + 1, indexOfOperation + num2Length));
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) ");
        }

        return expression;
    }

    public static String calc(String input) {
        String result = "";
        try {
            if (Integer.parseInt(values.getNum1()) >= 1 && Integer.parseInt(values.getNum1()) <= 10) {
                if (Integer.parseInt(values.getNum2()) >= 1 && Integer.parseInt(values.getNum2()) <= 10) {
                    result = "" + JS.eval(input);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Используются одновременно разные системы счисления - " + e);
        }
        return result;
    }

    public static String numstoArabian(){
        if(values.getNum1().equals("I")) values.setNum1(String.valueOf(1));
        if(values.getNum1().equals("II")) values.setNum1(String.valueOf(2));
        if(values.getNum1().equals("III")) values.setNum1(String.valueOf(3));
        if(values.getNum1().equals("IV")) values.setNum1(String.valueOf(4));
        if(values.getNum1().equals("V")) values.setNum1(String.valueOf(5));
        if(values.getNum1().equals("VI")) values.setNum1(String.valueOf(6));
        if(values.getNum1().equals("VII")) values.setNum1(String.valueOf(7));
        if(values.getNum1().equals("VIII")) values.setNum1(String.valueOf(8));
        if(values.getNum1().equals("IX")) values.setNum1(String.valueOf(9));
        if(values.getNum1().equals("X")) values.setNum1(String.valueOf(10));
        if(values.getNum2().equals("I")) values.setNum2(String.valueOf(1));
        if(values.getNum2().equals("II")) values.setNum2(String.valueOf(2));
        if(values.getNum2().equals("III")) values.setNum2(String.valueOf(3));
        if(values.getNum2().equals("IV")) values.setNum2(String.valueOf(4));
        if(values.getNum2().equals("V")) values.setNum2(String.valueOf(5));
        if(values.getNum2().equals("VI")) values.setNum2(String.valueOf(6));
        if(values.getNum2().equals("VII")) values.setNum2(String.valueOf(7));
        if(values.getNum2().equals("VIII")) values.setNum2(String.valueOf(8));
        if(values.getNum2().equals("IX")) values.setNum2(String.valueOf(9));
        if(values.getNum2().equals("X")) values.setNum2(String.valueOf(10));
        return calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum1() + ")");
    }
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C"};
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

}
