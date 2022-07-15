import jscheme.JS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Values values = new Values();

    public static void main(String[] args) throws IOException, ArithmeticException {
        divideString(new BufferedReader(new InputStreamReader(System.in)).readLine());
        try {
            System.out.println(calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum2() + ")"));
        }catch (NullPointerException e){
            System.out.println("Cтрока не является математической операцией - " + e);
        }
    }

    //Метод определяющий операнды и оператор в строке
    public static void divideString(String expression) {
        String operations = "[-+/*]";
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
    }

    //Метод вычисления выражения
    public static String calc(String input) {
        String result = "";
        try {
            if(values.getNum1().matches("^[0-9]*$") || values.getNum2().matches("^[0-9]*$")) {
                if (Integer.parseInt(values.getNum1()) >= 1 && Integer.parseInt(values.getNum1()) <= 10) {
                    if (Integer.parseInt(values.getNum2()) >= 1 && Integer.parseInt(values.getNum2()) <= 10) {
                        result = "" + JS.eval(input);
                    }
                }
            }
            else {
                String s = intToRoman(Integer.parseInt(numsToArabian()));
                if(!s.contains("I")){
                    throw new Exception("В римской системе нет отрицательных чисел.");
                }
                else{
                    System.out.println(s);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Используются одновременно разные системы счисления - " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Метод перевода в арабские числа
    public static String numsToArabian(){
        for (int i = 1; i < values.getArabicNums().length; i++) {
            if(values.getNum1().equals(values.getArabicNums()[i])) {
                values.setNum1(String.valueOf(i));
            }
            if(values.getNum2().equals(values.getArabicNums()[i])) {
                values.setNum2(String.valueOf(i));
            }
        }
        return calc("(" + values.getOperation() + " " + values.getNum1() + " " + values.getNum2() + ")");
    }

    //Метод перевода в римские числа
    public static String intToRoman(int num){
        String romanNum = "";
        for (int i = 1; i < values.getArabicNums().length; i++) {
            if(i == num) {
                romanNum = values.getArabicNums()[i];
            }
        }
        return romanNum;
    }
}
