import java.util.Scanner;

public class Calculator {

    private static String[] rome = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static int[] arabic =  {100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static void main(String[] args) throws Exception {
        int number1=0, number2=0, flag=0; //flag - введенные числа арабские цифры или римские
        String numberStr1="", numberStr2="";
        char operator;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please, enter arithmetic expression with two whole numbers less 11. You can use Rome or Arabic number and operator '+', '-', '*', '/'");
        String maths = scan.nextLine();
        maths = maths.replaceAll("\s", "");//удаляем пробелы если такие есть
        String[] data = maths.split("[+\\-\\*\\/]");

        //выброс ошибок
        if (data.length>2||data.length<=1) throw new Exception("You can use only 2 variables");//если более 2 переменных или только 1/
        if ((isNumeric(data[0])==true&&isNumeric(data[1])==false)||(isNumeric(data[1])==true&&isNumeric(data[0])==false))
            throw new Exception("Different types of variables ore one of them is not integer"); //если одна переменная String, другая int.

        //проверка: первый коэффициент это число или символ:
        if (isNumeric(data[0])) {
            number1= Integer.parseInt(data[0]);
            if (number1>10||number1<1) throw new Exception("You used number > 10 or < 1");
        }
        else {
            numberStr1=data[0];
            if(RomeToArab(numberStr1)>10||RomeToArab(numberStr1)<1)
                throw new Exception("You used number > 10 or < 1");
        }

        //проверка: второй коэффициент это число или символ:
        if (isNumeric(data[1])) {
            number2 = Integer.parseInt(data[1]);
            flag=1;
            if (number2>10||number2<1) throw new Exception("You used number > 10 or < 1");
        }
        else {
            numberStr2=data[1];
            if(RomeToArab(numberStr1)>10||RomeToArab(numberStr1)<1)
                throw new Exception("You used number > 10 or < 1");
        }

        //получение знака операции:
        operator=maths.charAt(data[0].length());

        //Арифметика:)
        if ((operator=='+')&&flag==1){
            System.out.println("Result: "+(number1+number2));
        }
        if ((operator=='+')&&flag==0){
            System.out.println("Result: "+ArabToRome(RomeToArab(numberStr1)+RomeToArab(numberStr2)));
        }
        if ((operator=='-')&&flag==1){
            System.out.println("Result: "+(number1-number2));
        }
        if ((operator=='-')&&flag==0){
            System.out.println("Result: "+ArabToRome(RomeToArab(numberStr1)-RomeToArab(numberStr2)));
            if (RomeToArab(numberStr1)-RomeToArab(numberStr2)<1){
                throw new Exception("Number 1 < Number 2");
            }
        }
        if ((operator=='*')&&flag==1){
            System.out.println("Result: "+(number1*number2));
        }
        if ((operator=='*')&&flag==0){
            System.out.println("Result: "+ArabToRome(RomeToArab(numberStr1)*RomeToArab(numberStr2)));
        }
        if ((operator=='/')&&flag==1){
            System.out.println("Result: "+(number1/number2));
        }
        if ((operator=='/')&&flag==0){
            System.out.println("Result: "+ArabToRome(RomeToArab(numberStr1)/RomeToArab(numberStr2)));
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int RomeToArab(String romeNumber) throws Exception {
        romeNumber=romeNumber.toUpperCase();//для тех у кого не работает Caps Lock :)
        StringBuffer romeBuffer = new StringBuffer(romeNumber);
        int romeLength, i=0, arabicNumber=0;
        while (romeBuffer.length()>0){
            if (romeBuffer.length()>1) romeLength=rome[i].length();
            else romeLength=1; /*если количество оставшихся символов в буфере romeBuffer больше 1, то сравниваем с массивом rome один или
            два символа в зависимости от количества символов в сравниваемом значении массива rome, иначе сравниваем только оставшийся */
            if(romeBuffer.substring(0, romeLength).equals(rome[i])==true)
            {
                arabicNumber+=arabic[i];
                romeBuffer.delete(0,rome[i].length());
                i=0;
            }
            else if (i<rome.length-1) i++;
            else throw new Exception("Not correct number");
        }
        return arabicNumber;
    }

    public static String ArabToRome(int arabNumber) throws Exception {
        StringBuffer arabBuffer = new StringBuffer();
        int i=0;
        while (arabNumber>0) {
            if (arabNumber >= arabic[i]) {
                arabNumber -= arabic[i];
                arabBuffer.append(rome[i]);
                i=0;
            }
            else if (i< rome.length-1) i++;
            else throw new Exception("Not correct number");;
        }
        return arabBuffer.toString();
    }
}
