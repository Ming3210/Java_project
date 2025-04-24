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
        int num = 0;
        boolean checkInt = false;

        while (!checkInt) {
            System.out.println(message);
            try {
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println(RED+"Không được để trống. Vui lòng nhập số nguyên."+RESET);
                    continue;
                }

                num = Integer.parseInt(input);
                checkInt = true;
            } catch (NumberFormatException e) {
                System.out.println(RED+"Giá trị nhập không hợp lệ, vui lòng nhập số nguyên."+ RESET);
            }
        }
        return num;
    }


    public static float checkFloat(String message, Scanner scanner) {
        boolean checkFloat = false;
        float num = 0;

        while (!checkFloat) {
            System.out.println(message);
            try {
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Không được để trống. Vui lòng nhập số thực.");
                    continue;
                }
                num = Float.parseFloat(input);
                checkFloat = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị nhập không hợp lệ, vui lòng nhập số thực.");
            }
        }
        return num;
    }

    public static double checkDouble(String message, Scanner scanner) {
        boolean checkDouble = false;
        double num = 0;

        while (!checkDouble) {
            System.out.println(message);
            try {
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Không được để trống. Vui lòng nhập số thực.");
                    continue;
                }
                num = Double.parseDouble(input);
                checkDouble = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị nhập không hợp lệ, vui lòng nhập số thực.");
            }
        }
        return num;
    }

    public static boolean checkBoolean(String message, Scanner scanner) {
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new Exception(RED+"Giá trị nhập ko hợp lệ, vui lòng nhập true hoặc false"+RESET);
                }
                if(input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(input);
                } else {
                    throw new Exception(RED+"Giá trị nhập ko hợp lệ, vui lòng nhập true hoặc false"+RESET);
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
                if (input.trim().isEmpty()) {
                    throw new Exception(RED+"Email không được để trống."+RESET);
                }
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
                if (input.trim().isEmpty()) {
                    throw new Exception(RED+"Email không được để trống."+RESET);
                }
                if (!input.matches("^[A-Za-z0-9+_.-]+@gmail.com+$")) {
                    throw new Exception(RED+"Giá trị nhập ko hợp lệ, hãy lập định dạnh email"+RESET);
                }

                if (studentService.checkEmailExist(input)) {
                    throw new Exception(RED+"Email đã tồn tại trong hệ thống"+RESET);
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
                if (input.trim().isEmpty()) {
                    throw new Exception(RED+"Số điện thoại không được để trống."+RESET);
                }

                if (!input.matches("(03|09|08)\\d{8}")) {
                    throw new Exception(RED+"Giá trị nhập ko hợp lệ, hãy lập định dạnh số điện thoại"+RESET);
                }
                checkPhone = true;
                return input;
            } catch (Exception e) {
                System.out.println(RED+"Giá trị nhập ko hợp lệ, hãy lập định dạnh số điện thoại"+RESET);
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
                if (input.trim().isEmpty()) {
                    System.out.println(RED+"Ngày không được để trống. Định dạng đúng là dd/MM/yyyy"+RESET);
                    continue;
                }

                LocalDate date = LocalDate.parse(input, formatter);

                if (date.isAfter(LocalDate.now())) {
                    throw new Exception(RED+"Ngày không được lớn hơn ngày hiện tại."+RESET);
                }

                return date;
            } catch (DateTimeParseException e) {
                System.out.println(RED+"Ngày không hợp lệ. Định dạng đúng là dd/MM/yyyy (ví dụ: 15/04/2000)"+RESET);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
