package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        String input = "";
        int targetBase;
        int sourceBase;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");

            String argumentsAsString = scanner.nextLine();
            String[] arguments = argumentsAsString.split(" ");
            if (arguments.length == 2) {
                sourceBase = Integer.parseInt(arguments[0]);
                targetBase = Integer.parseInt(arguments[1]);

                while (true) {
                    scanner = new Scanner(System.in);
                    System.out.print("Enter number in base {user source base} to convert to base {user target base} (To go back type /back) "
                            .replaceFirst("\\{user source base}", "" + sourceBase)
                            .replaceFirst("\\{user target base}", "" + targetBase));
                    input = scanner.next();
                    System.out.println();
                    if (input.equals("/back")) {
                        break;
                    }
                    BigDecimal number;
                    // Index 0 -> decimal part Index 1 -> fractional part
                    String[] numberParts = input.split("\\.");

                    if (sourceBase != 10) {
                        if (numberParts.length == 2) {
                            String decimalPart = convertToDecimalNumber(numberParts[0], sourceBase).toString();
                            String fractionalPart = convertToDecimalNumber(numberParts[1], sourceBase).toString();
                            number = new BigDecimal(decimalPart + "." + fractionalPart);
                        } else {
                            number = convertToDecimalNumber(input, sourceBase);
                        }
                    } else {
                        if (numberParts.length == 2) {
                            String decimalPart = new BigDecimal(numberParts[0]).toString();
                            String fractionalPart = new BigDecimal(numberParts[1]).toString();
                            number = new BigDecimal(decimalPart + "." + fractionalPart);
                        } else {
                            number = new BigDecimal(input);
                        }
                    }

                    String convertedNumber;
                    if (numberParts.length == 2) {
                        String[] convertedNumberParts = number.toString().split("\\.");
                        String decimalPart = convertDecimalNumber(new BigInteger(convertedNumberParts[0]), targetBase);
                        String fractionalPart = convertDecimalNumber(new BigInteger(convertedNumberParts[1]), targetBase);

                        if (decimalPart.equals("")){
                            decimalPart = "0";
                        }

                        while (fractionalPart.length() < numberParts[1].length()) {
                            fractionalPart += "0";
                        }

                        convertedNumber = decimalPart + "." + fractionalPart;
                    } else {
                        convertedNumber = convertDecimalNumber(new BigInteger(number.toString()), targetBase);
                    }

                    System.out.println("Conversion result: " + convertedNumber);
                    System.out.println();
                }
            } else {
                break;
            }
        }
    }

    private static String convertDecimalNumber(BigInteger numberInDecimal, int targetBase) {
        StringBuilder number = new StringBuilder();
        while (numberInDecimal.compareTo(BigInteger.valueOf(0)) == 1) {
            BigInteger reminder = numberInDecimal.divideAndRemainder(BigInteger.valueOf(targetBase))[1];
            char character = getCharacterFromNumber(parseInt(reminder.toString()));
            number.insert(0, character);
            numberInDecimal = numberInDecimal.divide(BigInteger.valueOf(targetBase));
        }
        return number.toString();
    }

    private static BigDecimal convertToDecimalNumber(String number, int sourceBase) {
        BigInteger convertedNumber = BigInteger.ZERO;
        int power = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            char character = number.charAt(i);
            BigInteger powerValue = BigInteger.valueOf(sourceBase).pow(power);
            convertedNumber = convertedNumber.add(getNumberFromCharacter(character).multiply(powerValue));
            power++;
        }
        return new BigDecimal(convertedNumber);
    }


    private static char getCharacterFromNumber(int reminder) {
        if (reminder < 10) {
            return String.valueOf(reminder).charAt(0);
        }
        return (char) (reminder + 55);
    }

    private static BigInteger getNumberFromCharacter(char hexCharacter) {
        hexCharacter = String.valueOf(hexCharacter).toUpperCase().charAt(0);
        if (hexCharacter >= 65 && hexCharacter <= 90) {
            return BigInteger.valueOf(hexCharacter - 55);
        }
        return new BigInteger("" + hexCharacter);
    }
}
