package ra.edu.validate;

import java.util.Scanner;

public class LoginValidator {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static String checkUsername(String message, Scanner scanner) {

        boolean checkString = false;
        System.out.println(message);
        while (!checkString) {
            try {
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new Exception(RED + "Username hoặc email không được để trống" + RESET);
                }

                return input.trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "";
    }

    public static String checkPassword(String message, Scanner scanner) {
        boolean checkString = false;
        System.out.println(message);
        while (!checkString) {
            try {
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new Exception(RED + "Password không được để trống" + RESET);
                }

                return input.trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "";
    }


}
