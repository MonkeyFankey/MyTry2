import java.util.Scanner;

public class NewCalc2 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        if (!expression.contains("\"")) throw new Exception("кавычки");
        String expression2= expression.replace("\"","");
        String result = splitString(expression2);
        if (result.length()>40){
            result.substring(0,39);
            System.out.println("\""+result+"...\"");
        }
        else {
        System.out.println("\""+result+"\"");}
    }
    public static String splitString(String expression2) throws Exception {
        String operator=detectOperator(expression2);
        return calcString(expression2,operator);

    }
    public static String detectOperator(String expression2) throws Exception {
        String oper ;
        if (expression2.contains("-")) oper = "-";
        else if (expression2.contains("+")) oper = "+" ;
        else if(expression2.contains("*")) oper ="*";
        else if(expression2.contains("/")) oper="/";
        else throw new Exception("неправильный оператор");
            return oper;
        }
    public static String calcString(String expression2, String operator) throws Exception {
        String action;
        String[] operands=expression2.split("[+\\-*/]");
        if (operands[0].length()>10 || operands[1].length()>10)throw new Exception("длинная строка");
        if (operator.equals("-")){
            String first = operands[0];
            String second = operands[1];
            action =first.replace(second,"");
        }
        else if (operator.equals("+")){
            action = operands[0]+operands[1];
        }
        else if(operator.equals("/")) {
            boolean check= tryInt(operands);
            if (check) throw new Exception("Число на 1 месте");
            int d = Integer.parseInt(operands[1]);
            if (d > 10) throw new Exception("большое число");
            int del = operands[0].length() / d;
            action = operands[0].substring(0, del);
        }
        else if (operator.equals("*")){
            boolean check= tryInt(operands);
            if (check) throw new Exception("Число на 1 месте");
            int r= Integer.parseInt(operands[1]);
            if  (r > 10) throw new Exception("большое число");
            action =operands[0].repeat(r);
        }
        else throw new Exception("неподдерживаемая операция");
        return action;
}
public static boolean tryInt(String[] operands) throws Exception {
        try{
            int x = Integer.parseInt(operands[0]);
            return true;
        }
        catch (NumberFormatException e) {
            return false;}

    }
}

