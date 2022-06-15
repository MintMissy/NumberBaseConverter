package converter;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String actionType = "";

        int decimalNumber = 0;
        String decimalConversionResult = "";
        int targetBase = 2;

        String notDecimalNumber = "";
        int notDecimalConversionResult = 0;
        int sourceBase = 2;

        while (!actionType.equals("/exit")) {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            actionType = scanner.next();

            if (actionType.equals("/from")) {
                System.out.print("\nEnter a number in decimal system: ");
                decimalNumber = scanner.nextInt();
                System.out.print("\nEnter the target base: ");
                targetBase = scanner.nextInt();

                decimalConversionResult = convertDecimalNumber(decimalNumber, targetBase);
                System.out.println("Conversion result: " + decimalConversionResult);
            } else if (actionType.equals("/to")) {
                System.out.print("\nEnter source number: ");
                notDecimalNumber = scanner.next();
                System.out.print("\nEnter source base: ");
                sourceBase = scanner.nextInt();

                notDecimalConversionResult = convertToDecimalNumber(notDecimalNumber, sourceBase);
                System.out.println("Conversion to decimal result: " + notDecimalConversionResult);
            } else if (actionType.equals("/exit")) {
                break;
            }
        }
    }

    private static String convertDecimalNumber(int numberInDecimal, int targetBase) {
        StringBuilder number = new StringBuilder();
        while (numberInDecimal > 0) {
            int reminder = numberInDecimal % targetBase;
            char character = getHexCharacter(reminder);
            number.insert(0, character);
            numberInDecimal /= targetBase;
        }
        return number.toString();
    }

    private static int convertToDecimalNumber(String number, int sourceBase) {
        int convertedNumber = 0;
        int power = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            char character = number.charAt(i);
            convertedNumber += getNumberFromHex(character) * Math.pow(sourceBase, power);
            power++;
        }
        return convertedNumber;
    }


    private static char getHexCharacter(int reminder) {
        if (reminder < 10) {
            return String.valueOf(reminder).charAt(0);
        }
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

    private static int getNumberFromHex(char hexCharacter) {
        switch (String.valueOf(hexCharacter).toUpperCase().charAt(0)) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
        }
        return Integer.parseInt("" + hexCharacter);
    }
}
