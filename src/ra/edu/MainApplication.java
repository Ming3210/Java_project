package ra.edu;

import ra.edu.business.model.Session;
import ra.edu.business.model.Student;
import ra.edu.business.service.login.LoginService;
import ra.edu.business.service.login.LoginServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.LoginValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.*;
import static ra.edu.presentation.StudentUI.showStudentMenu;

public class MainApplication {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
    public static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginServiceImp();
        StudentService studentService = new StudentServiceImp();

        while (isRunning) {
            System.out.println(PURPLE + BOLD + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + BOLD + "║                                            HỆ THỐNG ĐĂNG NHẬP                                            ║" + RESET);
            System.out.println(PURPLE + BOLD + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);

            int loginResult = 0;

            while (loginResult == 0) {
                System.out.println(PURPLE + BOLD + "Nhấn 0 để thoát" + RESET);
                String username = LoginValidator.checkUsername(CYAN + "Tên đăng nhập hoặc email: " + RESET, scanner);
                if (username.equals("0")) {
                    System.out.println(RED + "👋 Tạm biệt!" + RESET);
                    isRunning = false;
                    return;
                }
                String password = LoginValidator.checkPassword(CYAN + "Mật khẩu: " + RESET, scanner);

                loginResult = loginService.checkLogin(username, password);

                switch (loginResult) {
                    case 1:
                        System.out.println(GREEN + "Đăng nhập thành công với tư cách Admin!" + RESET);
                        showMainMenu(scanner);
                        loginResult = 0;
                        break;
                    case 2:
                        System.out.println(GREEN + "Đăng nhập thành công với tư cách Sinh viên!" + RESET);
                        Student student = studentService.getStudentByEmail(username);
                        if (student != null) {
                            Session.currentStudent = student;
                            System.out.println(PURPLE + "Xin chào " + student.getName() + "!" + RESET);
                            showStudentMenu(scanner);
                            loginResult = 0;
                        } else {
                            System.out.println(RED + "Có lỗi trong quá trình đăng nhập " + RESET);
                            loginResult = 0;
                        }
                        break;
                    default:
                        System.out.println(RED + "Sai thông tin đăng nhập. Vui lòng thử lại!\n" + RESET);
                }
            }
        }
    }

}