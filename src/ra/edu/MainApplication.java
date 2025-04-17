package ra.edu;

import ra.edu.business.service.login.LoginService;
import ra.edu.business.service.login.LoginServiceImp;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.showAdminMenu;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginServiceImp();

        int loginResult = 0;

        System.out.println("===== HỆ THỐNG ĐĂNG NHẬP =====");

        while (loginResult == 0) {
            System.out.print("Tên đăng nhập: ");
            String username = scanner.nextLine();

            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();

            loginResult = loginService.checkLogin(username, password);

            switch (loginResult) {
                case 1:
                    System.out.println("Đăng nhập thành công với tư cách Admin!");
                    showAdminMenu(scanner);
                    break;
                case 2:
                    System.out.println("Đăng nhập thành công với tư cách Sinh viên!");
                    showStudentMenu(scanner);
                    break;
                default:
                    System.out.println("Sai thông tin đăng nhập. Vui lòng thử lại!\n");
            }
        }
    }



    public static void showStudentMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== MENU SINH VIÊN =====");
            System.out.println("1. Xem thông tin cá nhân");
            System.out.println("2. Đổi mật khẩu");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("→ Hiển thị thông tin sinh viên");
                    break;
                case "2":
                    System.out.println("→ Chức năng đổi mật khẩu");
                    break;
                case "0":
                    System.out.println("👋 Đăng xuất thành công!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ.");
            }
        }
    }
}
