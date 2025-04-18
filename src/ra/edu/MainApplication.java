package ra.edu;

import ra.edu.business.service.login.LoginService;
import ra.edu.business.service.login.LoginServiceImp;
import ra.edu.validate.LoginValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.showMainMenu;

public class MainApplication {
    // Mã màu ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginServiceImp();

        int loginResult = 0;

        System.out.println(PURPLE + BOLD + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(PURPLE + BOLD + "║                                            HỆ THỐNG ĐĂNG NHẬP                                            ║" + RESET);
        System.out.println(PURPLE + BOLD + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);

        while (loginResult == 0) {
            String username = LoginValidator.checkUsername(CYAN + "Tên đăng nhập hoặc email: " + RESET, scanner);
            String password = LoginValidator.checkPassword(CYAN +  "Mật khẩu: " + RESET, scanner);

            loginResult = loginService.checkLogin(username, password);

            switch (loginResult) {
                case 1:
                    System.out.println(GREEN + "Đăng nhập thành công với tư cách Admin!" + RESET);
                    showMainMenu(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "Đăng nhập thành công với tư cách Sinh viên!" + RESET);
                    showStudentMenu(scanner);
                    break;
                default:
                    System.out.println(RED + "Sai thông tin đăng nhập. Vui lòng thử lại!\n" + RESET);
            }
        }
    }

    public static void showStudentMenu(Scanner scanner) {
        while (true) {
            System.out.println(BLUE + BOLD + "╔══════════════════════════════════════╗" + RESET);
            System.out.println(BLUE + BOLD + "║            MENU SINH VIÊN            ║" + RESET);
            System.out.println(BLUE + BOLD + "╠══════════════════════════════════════╣" + RESET);
            System.out.println(BLUE + "║ 1. Xem thông tin cá nhân             ║" + RESET);
            System.out.println(BLUE + "║ 2. Đổi mật khẩu                      ║" + RESET);
            System.out.println(BLUE + "║ 0. Đăng xuất                         ║" + RESET);
            System.out.println(BLUE + BOLD + "╚══════════════════════════════════════╝" + RESET);
            System.out.print(CYAN + "Chọn chức năng: " + RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(GREEN + "📋 Hiển thị thông tin sinh viên" + RESET);
                    break;
                case "2":
                    System.out.println(GREEN + "🔒 Chức năng đổi mật khẩu" + RESET);
                    break;
                case "0":
                    System.out.println(RED + "👋 Đăng xuất thành công!" + RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(RED + "⚠ Lựa chọn không hợp lệ." + RESET);
            }
        }
    }
}
