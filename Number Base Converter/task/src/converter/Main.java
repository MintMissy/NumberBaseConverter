package converter;

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
                    System.out.print("Enter number in base {user source base} to convert to base {user target base} (To go back type /back) "
                            .replaceFirst("\\{user source base\\}", "" + sourceBase)
                            .replaceFirst("\\{user target base\\}", "" + targetBase));
                    input = scanner.next();
                    System.out.println();
                    if (input.equals("/back")) {
                        break;
                    }
                    BigInteger decimalNumber;
                    if (sourceBase != 10){
                        decimalNumber = convertToDecimalNumber(input, sourceBase);
                    }else{
                        decimalNumber = new BigInteger(input);
                    }
                    String convertedNumber = convertDecimalNumber(decimalNumber, targetBase);
                    System.out.println("Conversion result: " + convertedNumber);
                    System.out.println(decimalNumber);
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

    private static BigInteger convertToDecimalNumber(String number, int sourceBase) {
        BigInteger convertedNumber = BigInteger.ZERO;
        int power = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            char character = number.charAt(i);
            BigInteger powerValue = BigInteger.valueOf(sourceBase).pow(power);
            convertedNumber = convertedNumber.add(getNumberFromCharacter(character).multiply(powerValue));
            power++;
        }
        return convertedNumber;
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
