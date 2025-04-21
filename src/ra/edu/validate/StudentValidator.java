package ra.edu.validate;

import ra.edu.business.service.student.StudentService;

import java.util.Scanner;

public class StudentValidator {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static String inputNewStudentId(String message, Scanner scanner, StudentService studentService) {
        System.out.println(message);
        boolean isValidId = false;
        String id = null;

        while (!isValidId) {
            try {
                id = scanner.nextLine();

                if (id.isEmpty()) {
                    throw new Exception(RED + "Id không được để trống" + RESET);
                }

                if (!id.matches("^S\\w{4}$")) {
                    throw new Exception("Id phải bắt đầu bằng chữ \"S\" và theo sau là 4 kí tự bất kỳ.");
                }

                if (studentService.isStudentIdExists(id)) {
                    throw new Exception(RED+"Id sinh viên đã tồn tại trong hệ thống."+RESET);
                }

                isValidId = true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return id;
    }

    public static String inputExistingStudentId(String message, Scanner scanner, StudentService studentService) {
        System.out.println(message);
        while (true) {
            try {
                String id = scanner.nextLine();
                if (id.isEmpty()) {
                    throw new Exception(RED + "Id không được để trống" + RESET);
                }
                if (!id.matches("^S\\w{4}$")) {
                    throw new Exception("Id phải bắt đầu bằng chữ \"S\" và theo sau là 4 kí tự bất kỳ.");
                }

                if (!studentService.isStudentIdExists(id)) {
                    throw new Exception("Id sinh viên không tồn tại trong hệ thống.");
                }

                return id;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
