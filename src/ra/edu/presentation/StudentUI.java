package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.model.RegisteredCourseDTO;
import ra.edu.business.model.Session;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;


public class StudentUI {
    static CourseService courseService = new CourseServiceImp();
    static StudentService studentService = new StudentServiceImp();

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
    public static final String MAGENTA = "\u001B[38;5;214m";

    public static void showStudentMenu(Scanner scanner) {
        while (true) {
            System.out.println(BLUE + BOLD + "╔══════════════════════════════════════╗" + RESET);
            System.out.println(BLUE + BOLD + "║            MENU SINH VIÊN            ║" + RESET);
            System.out.println(BLUE + BOLD + "╠══════════════════════════════════════╣" + RESET);
            System.out.println(BLUE + "║ 1. Xem danh sách khóa học            ║" + RESET);
            System.out.println(BLUE + "║ 2. Đăng ký khóa học                  ║" + RESET);
            System.out.println(BLUE + "║ 3. Xen khóa học đã đăng ký           ║" + RESET);
            System.out.println(BLUE + "║ 4. Hủy đăng ký                       ║" + RESET);
            System.out.println(BLUE + "║ 5. Cập nhật mật khẩu                 ║" + RESET);
            System.out.println(BLUE + "║ 0. Đăng xuất                         ║" + RESET);
            System.out.println(BLUE + BOLD + "╚══════════════════════════════════════╝" + RESET);
            int choice = Validator.checkInt(CYAN + "Chọn chức năng: " + RESET, scanner);

            switch (choice) {
                case 1:
                    System.out.println(GREEN + "📋 Xem danh sách khóa học" + RESET);
                    paginatedCourse(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "🔒 Đăng ký khóa học" + RESET);
                    registerEnrollment(scanner);
                    break;
                case 3:
                    System.out.println(GREEN + "📜 Xem khóa học đã đăng ký" + RESET);
                    showAllRegistedEnrollment(scanner);
                    break;
                case 4:
                    System.out.println(GREEN + "❌ Hủy đăng ký" + RESET);
                    cancelEnrollment(scanner);
                    break;
                case 5:
                    System.out.println(GREEN + "🔑 Cập nhật mật khẩu" + RESET);
                    updatePassword(scanner);
                    break;
                case 0:
                    System.out.println(RED + "👋 Đăng xuất thành công!" + RESET);
                    return;
                default:
                    System.out.println(RED + "⚠ Lựa chọn không hợp lệ." + RESET);
            }
        }
    }


    public static void paginatedCourse(Scanner scanner){
        int totalPages = courseService.getTotalPages();
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);

        int currentPage = 1;
        boolean continuePaging = true;
        boolean isEdge = false;

        while (continuePaging) {
            if (!isEdge){
                System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                List<Course> courseList = courseService.getCoursesByPage(currentPage);

                if (courseList.isEmpty()) {
                    System.out.println(BOLD + RED + "Không có khóa học nào." + RESET);
                } else {
                    System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH KHÓA HỌC ❮❮❮" + RESET);
                    System.out.println(BOLD + "╔════════╦═══════════════════════════════════╦═══════════════╦════════════════════╗" + RESET);
                    System.out.println(BOLD + "║   ID   ║              TÊN KHÓA HỌC         ║ THỜI LƯỢNG    ║    GIẢNG VIÊN      ║" + RESET);
                    System.out.println(BOLD + "╠════════╬═══════════════════════════════════╬═══════════════╬════════════════════╣" + RESET);

                    for (Course course : courseList) {
                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-33s " + RESET + BOLD + "║" + YELLOW + " %-13s " + RESET + BOLD + "║" + MAGENTA + " %-18s " + RESET + BOLD + "║\n" + RESET,
                                course.getCourseId(), course.getCourseName(), course.getDuration() + " phút", course.getInstructor());
                    }

                    System.out.println(BOLD + "╚════════╩═══════════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

                }
            }

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET +   "                      " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET +   "                       " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET +   "                      " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý khóa học" + RESET +  "  " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
            System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
            String paginationChoice = scanner.nextLine();
            isEdge = false;

            switch (paginationChoice) {
                case "1":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case "2":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case "3":
                    System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages + "): " + RESET);
                    try {
                        int selectedPage = Integer.parseInt(scanner.nextLine());
                        if (selectedPage >= 1 && selectedPage <= totalPages) {
                            currentPage = selectedPage;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages + RESET);
                            isEdge = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                        isEdge = true;
                    }
                    break;
                case "0":
                    continuePaging = false;
                    break;
                default:
                    System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                    isEdge = true;
            }
        }
    }


    public static void registerEnrollment(Scanner scanner){
        String courseId = CourseValidator.inputExistingCourseId("→ Nhập ID khóa học muốn đăng kí: ", scanner, courseService);
//        System.out.println(Session.currentStudent.getStudentId());
//        System.out.println(courseId);
        String studentId = Session.currentStudent.getStudentId();
        try {
            studentService.registerEnrollment(studentId, courseId);
            System.out.println(GREEN + "Đăng ký khóa học thành công!" + RESET);
        } catch (Exception e) {
            System.err.println(RED  + e.getMessage() + RESET);
        }
    }

    public static void showAllRegistedEnrollment(Scanner scanner){
        String studentId = Session.currentStudent.getStudentId();
        int totalPages = studentService.getTotalRegistedEnrollmentPages(studentId);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        int currentPage = 1;
        boolean continuePaging = true;
        boolean isEdge = false;
        while (continuePaging){
            if (!isEdge){
                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                List<RegisteredCourseDTO> registeredEnrollment = studentService.getRegistedEnrollmentByPage(studentId, currentPage);
                if (registeredEnrollment.isEmpty()){
                    System.out.println(BOLD + RED + "Không có khóa học nào." + RESET);
                } else {
                    System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH KHÓA HỌC ĐÃ ĐĂNG KÝ ❮❮❮" + RESET);
                    System.out.println(BOLD + "╔════════╦═══════════════════════════╦═══════════════╗" + RESET);
                    System.out.println(BOLD + "║   ID   ║         TÊN KHÓA HỌC      ║ STATUS        ║" + RESET);
                    System.out.println(BOLD + "╠════════╬═══════════════════════════╬═══════════════╣" + RESET);

                    for (RegisteredCourseDTO enrollment : registeredEnrollment) {
                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-25s "  +
                                        RESET+ BOLD +"║"+ YELLOW +" %-13s "+RESET+ BOLD +"║"+ BOLD+"\n" +RESET,
                                enrollment.getCourseId(), enrollment.getCourseName(), enrollment.getStatus());
                    }

                    System.out.println(BOLD+"╚════════╩═══════════════════════════╩═══════════════╝"+RESET);

                }
            }

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET +   "                      " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET +   "                       " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET +   "                      " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý khóa học" + RESET +  "  " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
            System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
            String paginationChoice = scanner.nextLine();
            isEdge = false;

            switch (paginationChoice) {
                case "1":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case "2":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case "3":
                    System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages + "): " + RESET);
                    try {
                        int selectedPage = Integer.parseInt(scanner.nextLine());
                        if (selectedPage >= 1 && selectedPage <= totalPages) {
                            currentPage = selectedPage;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages + RESET);
                            isEdge = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                        isEdge = true;
                    }
                    break;
                case "0":
                    continuePaging = false;
                    break;
                default:
                    System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                    isEdge = true;
            }

        }
    }


    public static void cancelEnrollment(Scanner scanner){
        String courseId = CourseValidator.inputExistingCourseId("→ Nhập ID khóa học muốn hủy đăng kí: ", scanner, courseService);
        String studentId = Session.currentStudent.getStudentId();
        try {
            studentService.cancelEnrollment(studentId, courseId);
            System.out.println(GREEN + "Hủy đăng ký khóa học thành công!" + RESET);
        } catch (Exception e) {
            System.out.println(RED  + e.getMessage() + RESET);
        }
    }


    public static void updatePassword(Scanner scanner){
        String studentId = Session.currentStudent.getStudentId();
        String oldPassword = Validator.checkString("→ Nhập mật khẩu cũ: ", scanner, 6, 20);
        boolean isDuplicate = false;
        boolean validPassword =  studentService.checkOldPassword(studentId, oldPassword);
        String newPassword = Validator.checkString("→ Nhập mật khẩu mới: ", scanner, 6, 20);
        String confirmPassword = Validator.checkString("→ Nhập lại mật khẩu mới: ", scanner, 6, 20);
        if (newPassword.equals(oldPassword)){
            isDuplicate = true;
            System.out.println(RED + "Mật khẩu mới không được trùng với mật khẩu cũ!" + RESET);
        }
        if (validPassword && !isDuplicate) {
            if (newPassword.equals(confirmPassword)) {
                studentService.updatePassword(studentId, newPassword);
                System.out.println(GREEN + "Cập nhật mật khẩu thành công!" + RESET);
            } else {
                System.out.println(RED + "Mật khẩu xác nhận không khớp!" + RESET);
            }
        }


    }
}
