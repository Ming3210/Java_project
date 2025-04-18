package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static int checkInt(String message, Scanner scanner) {
        boolean checkInt = false;
        System.out.println(message);
        while (!checkInt) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine();
                checkInt = true;
                return num;
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, vui lòng nhập số nguyên");
                scanner.nextLine();
            }
        }
        return 0;
    }

    public static float checkFloat(String message, Scanner scanner) {
        boolean checkFloat = false;
        System.out.println(message);
        while (!checkFloat) {
            try {
                float num = scanner.nextFloat();
                scanner.nextLine();
                checkFloat = true;
                return num;
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, vui lòng nhập float");
                scanner.nextLine();
            }
        }
        return 0;
    }

    public static double checkDouble(String message, Scanner scanner) {
        boolean checkDouble = false;
        System.out.println(message);
        while (!checkDouble) {
            try {
                double num = scanner.nextDouble();
                scanner.nextLine();
                checkDouble = true;
                return num;
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, vui lòng nhập số th phân");
                scanner.nextLine();
            }
        }
        return 0;
    }

    public static boolean checkBoolean(String message, Scanner scanner) {
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new Exception("Giá trị nhập ko hợp lệ, vui lòng nhập true hoặc false");
                }
                if(input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(input);
                } else {
                    throw new Exception("Giá trị nhập ko hợp lệ, vui lòng nhập true hoặc false");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String checkString(String message, Scanner scanner, int min, int max){
        boolean checkString = false;
        System.out.println(message);
        while (!checkString) {
            try {
                String input = scanner.nextLine();
                if(input.isEmpty() || input.length() < min || input.length() > max){
                    throw new Exception("Giá trị nhập ko hợp lệ, vui lòng nhập chuỗi từ " + min + " đến " + max + " ký tự và không có chuỗi rỗng");
                }
                return input.trim();
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, vui lòng nhập chuỗi từ " + min + " đến " + max + " ký tự và không có khoảng trắng đầu và cuối");
            }
        }
        return "";
    }

    public static String checkEmail(String message, Scanner scanner){
        boolean checkEmail = false;
        System.out.println(message);
        while (!checkEmail) {
            try {
                String input = scanner.nextLine();
                if (!input.matches("^[A-Za-z0-9+_.-]+@gmail.com+$")) {
                    throw new Exception("Giá trị nhập ko hợp lệ, hãy lập định dạnh email");
                }
                checkEmail = true;
                return input;
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, hãy lập định dạnh email");
            }
        }
        return "";
    }

    public static String checkPhone(String message, Scanner scanner){
        boolean checkPhone = false;
        System.out.println(message);
        while (!checkPhone) {
            try {
                String input = scanner.nextLine();
                if (!input.matches("(03|09|08)\\d{8}")) {
                    throw new Exception("Giá trị nhập ko hợp lệ, hãy lập định dạnh số điện thoại");
                }
                checkPhone = true;
                return input;
            } catch (Exception e) {
                System.out.println("Giá trị nhập ko hợp lệ, hãy lập định dạnh số điện thoại");
            }
        }
        return "";
    }

    public static LocalDate checkDate(String message, Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine();
                LocalDate date = LocalDate.parse(input, formatter);

                if (date.isAfter(LocalDate.now())) {
                    throw new Exception("Ngày không được lớn hơn ngày hiện tại.");
                }

                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Định dạng đúng là dd/MM/yyyy (ví dụ: 15/04/2024)");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
