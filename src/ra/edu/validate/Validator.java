package ra.edu.validate;

import ra.edu.business.service.student.StudentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
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

    public static String checkString(String message, Scanner scanner, int min, int max) {
        boolean checkString = false;
        System.out.println(message);
        while (!checkString) {
            try {
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new Exception(RED + "Chuỗi không được để trống" + RESET);
                }

                if (input.length() < min || input.length() > max) {
                    throw new Exception(RED + "Vui lòng nhập chuỗi từ " + min + " đến " + max + " ký tự" + RESET);
                }

                if (!input.equals(input.trim())) {
                    throw new Exception(RED + "Không được có khoảng trắng ở đầu hoặc cuối chuỗi" + RESET);
                }

                if (input.matches(".*\\s{2,}.*")) {
                    throw new Exception(RED + "Không được có nhiều hơn một khoảng trắng giữa các từ" + RESET);
                }

                return input.trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
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

    public static String checkExistEmail(String message, Scanner scanner, StudentService studentService){
        boolean checkEmail = false;
        System.out.println(message);
        while (!checkEmail) {
            try {
                String input = scanner.nextLine();
                if (!input.matches("^[A-Za-z0-9+_.-]+@gmail.com+$")) {
                    throw new Exception("Giá trị nhập ko hợp lệ, hãy lập định dạnh email");
                }
                if (studentService.checkEmailExist(input)) {
                    throw new Exception("Email đã tồn tại trong hệ thống");
                }
                checkEmail = true;
                return input;
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
