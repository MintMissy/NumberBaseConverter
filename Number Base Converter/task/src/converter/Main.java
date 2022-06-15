package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number in decimal system: ");
        int numberInDecimal = scanner.nextInt();

        System.out.print("Enter target base: ");
        int targetBase = scanner.nextInt();

        String conversionResult = convertNumber(numberInDecimal, targetBase);
        System.out.println("Conversion result: " + conversionResult);
    }

    private static String convertNumber(int numberInDecimal, int targetBase) {
        StringBuilder number = new StringBuilder();
        while (numberInDecimal > 0) {
            int reminder = numberInDecimal % targetBase;
            char character = reminder > 9 ? getHexCharacter(reminder) : String.valueOf(reminder).charAt(0);
            number.insert(0, character);
            numberInDecimal /= targetBase;
        }
        return number.toString();
    }

    private static char getHexCharacter(int reminder){
        switch (reminder) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
        }
        return '0';
    }
}
