package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.model.RegisteredCourseDTO;
import ra.edu.business.model.Session;
import ra.edu.business.model.Student;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentService;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
    static CourseService courseService = new CourseServiceImp();
    static StudentService studentService = new StudentServiceImp();
    static EnrollmentService enrollmentService = new EnrollmentServiceImp();

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
    public static final String MAGENTA = "\u001B[38;5;214m";  // Cam (Mã ANSI 214)

    public static void showMainMenu(Scanner scanner) {

        do {
            System.out.println(PURPLE + BOLD +
                    "\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n" +
                    "║                                     MENU ADMIN                                                           ║\n" +
                    "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║                                                                                                          ║");
            System.out.println("║     1. Quản lý khóa học                                                                                  ║");
            System.out.println("║                                                                                                          ║");
            System.out.println("║     2. Quản lý sinh viên                                                                                 ║");
            System.out.println("║                                                                                                          ║");
            System.out.println("║     3. Thống kê                                                                                          ║");
            System.out.println("║                                                                                                          ║" + RESET);
            System.out.println(RED + "║     0. Đăng xuất                                                                                         ║");
            System.out.println("║                                                                                                          ║" + RESET);
            System.out.println(PURPLE + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);

            int choice = Validator.checkInt(YELLOW + "🛠 Chọn chức năng: " + RESET, scanner);

            switch (choice) {
                case 1:
                    System.out.println(GREEN + "📘 → Quản lý khóa học" + RESET);
                    showCourseManagementMenu(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "👨‍🎓 → Quản lý sinh viên" + RESET);
                    showStudentManagementMenu(scanner);
                    break;
                case 3:
                    System.out.println(GREEN + "📊 → Thống kê" + RESET);
                    showStatisticsMenu(scanner);
                    break;
                case 0:
                    System.out.println(RED + "👋 Đăng xuất thành công!" + RESET);
                    return;
                default:
                    System.out.println(RED + "⚠ Lựa chọn không hợp lệ." + RESET);
            }
        } while (true);
    }


    public static void showCourseManagementMenu(Scanner scanner) {

        do {
            System.out.println(PURPLE + BOLD +
                    "\n╔═════════════════════════════════════════════════════════════════════╗\n" +
                "║           QUẢN LÝ KHÓA HỌC                                          ║\n" +
                    "╠═════════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ 1. Hiển thị danh sách khóa học                                      ║");
        System.out.println("║ 2. Thêm mới khóa học                                                ║");
        System.out.println("║ 3. Chỉnh sửa thông tin khóa học                                     ║");
        System.out.println("║ 4. Xóa khóa học theo ID                                             ║");
        System.out.println("║ 5. Tìm kiếm khóa học theo tên                                       ║");
        System.out.println("║ 6. Sắp xếp khóa học                                                 ║");
        System.out.println("║ 7. Hiển thị danh sách sinh viên đăng ký theo từng khóa học          ║");
        System.out.println("║ 8. Duyệt sinh viên đăng ký khóa học                                 ║");
        System.out.println("║ 9. Xóa sinh viên đăng ký khóa học                                   ║" + RESET);
            System.out.println(RED + "║ 0. Quay lại menu chính                                              ║" + RESET);
            System.out.println(PURPLE + "╚═════════════════════════════════════════════════════════════════════╝" + RESET);

            int choice = Validator.checkInt(YELLOW + "📘 Chọn chức năng: " + RESET, scanner);

            switch (choice) {
                case 1:
                    System.out.println(GREEN + "📚 → Hiển thị danh sách khóa học" + RESET);
                    paginatedCourse(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "➕ → Thêm mới khóa học" + RESET);
                    addCourse(scanner);
                    break;
                case 3:
                    System.out.println(CYAN + "✏️ → Chỉnh sửa thông tin khóa học" + RESET);
                    showUpdateCourseMenu(scanner);
                    break;
                case 4:
                    System.out.println(RED + "🗑 → Xóa khóa học" + RESET);
                    deleteCourse(scanner);
                    break;
                case 5:
                    System.out.println(GREEN + "🔍 → Tìm kiếm khóa học theo tên" + RESET);
                    searchCourseByName(scanner);
                    break;
                case 6:
                    System.out.println(GREEN + "🔃 → Sắp xếp khóa học" + RESET);
                    showSortCourseMenu(scanner);
                    break;
                case 7:
                    displayStudentByEnrollmentList(scanner);
                    break;
                case 8:
                    System.out.println(GREEN + "✅ → Duyệt sinh viên đăng ký khóa học" + RESET);
                    approveEnrollment(scanner);
                    break;
                case 9:
                    System.out.println(RED + "🗑 → Xóa sinh viên đăng ký khóa học" + RESET);
                    deniedEnrollment(scanner);
                    break;
                case 0:
                    System.out.println(YELLOW + "🔙 Quay lại menu chính..." + RESET);
                    return;
                default:
                    System.out.println(RED + "⚠ Lựa chọn không hợp lệ. Vui lòng thử lại!" + RESET);
            }
        } while (true);
    }

    public static void showStudentManagementMenu(Scanner scanner) {
        do {
            System.out.println(PURPLE + BOLD +
                    "\n╔══════════════════════════════════════════╗\n" +
                    "║          QUẢN LÝ SINH VIÊN               ║\n" +
                    "╠══════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ 1. Hiển thị danh sách sinh viên          ║");
            System.out.println("║ 2. Thêm mới sinh viên                    ║");
            System.out.println("║ 3. Xóa sinh viên theo ID                 ║");
            System.out.println("║ 4. Tìm kiếm sinh viên theo tên           ║");
            System.out.println("║ 5. Sắp xếp sinh viên                     ║" + RESET);
            System.out.println(RED + "║ 0. Quay lại menu chính                   ║" + RESET);
            System.out.println(PURPLE + "╚══════════════════════════════════════════╝" + RESET);

            int choice = Validator.checkInt(YELLOW + "🌟 Chọn chức năng: " + RESET, scanner);

            switch (choice) {
                case 1:
                    System.out.println(GREEN + "📋 → Hiển thị danh sách sinh viên" + RESET);
                    paginatedStudent(scanner);
                    break;
                case 2:
                    System.out.println(GREEN + "➕ → Thêm mới sinh viên" + RESET);
                    addStudent(scanner);
                    break;
                case 3:
                    System.out.println(RED + "🗑 → Xóa sinh viên" + RESET);
                    deleteStudent(scanner);
                    break;
                case 4:
                    System.out.println(GREEN + "🔍 → Tìm kiếm sinh viên theo tên" + RESET);
                    searchStudent(scanner);
                    break;
                case 5:
                    System.out.println(GREEN + "🔃 → Sắp xếp sinh viên" + RESET);
                    showSortStudentMenu(scanner);
                    break;
                case 0:
                    System.out.println(YELLOW + "🔙 Quay lại menu chính..." + RESET);
                    return;
                default:
                    System.out.println(RED + "⚠ Lựa chọn không hợp lệ. Vui lòng thử lại!" + RESET);
            }
        } while (true);
    }


    public static void showSortCourseMenu(Scanner scanner) {
        do {
            System.out.println("\n== SẮP XẾP KHÓA HỌC ==");
            System.out.println("1. Theo tên (A → Z)");
            System.out.println("2. Theo tên (Z → A)");
            System.out.println("3. Theo ID (tăng dần)");
            System.out.println("4. Theo ID (giảm dần)");
            System.out.println("0. Quay lại menu quản lý khóa học");
            int choice = Validator.checkInt("Chọn kiểu sắp xếp: ", scanner);

            System.out.println("→ Danh sách đã sắp xếp:");
            switch (choice) {
                case 1:
                    System.out.println(BOLD + GREEN + "→ Sắp xếp theo tên (A → Z)" + RESET);
                    int totalPages = courseService.getTotalPages();
                    int currentPage = 1;
                    boolean continuePaging = true;
                    boolean isEdge = false;
                    while (continuePaging) {
                        if (!isEdge){
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Course> courseList = courseService.SortCoursesByNameAsc(currentPage);

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
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET +  "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET +  "           " + BOLD + PURPLE + " │" + RESET);
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
                    break;
                case 2:
                    System.out.println("→ Sắp xếp theo tên (Z → A)");
                    int totalPages2 = courseService.getTotalPages();
                    int currentPage2 = 1;
                    boolean continuePaging2 = true;
                    boolean isEdge2 = false;
                    while (continuePaging2) {
                        if (!isEdge2){
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage2 + "/" + totalPages2 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Course> courseList = courseService.SortCoursesByNameDesc(currentPage2);

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
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge2 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage2 < totalPages2) {
                                    currentPage2++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage2 > 1) {
                                    currentPage2--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages2 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages2) {
                                        currentPage2 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages2 + RESET);
                                        isEdge2 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge2 = true;
                                }
                                break;
                            case "0":
                                continuePaging2 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge2 = true;
                        }
                    }
                    break;
                case 3:
                    System.out.println(BOLD + GREEN + "→ Sắp xếp theo ID (tăng dần)" + RESET);
                    int totalPages3 = courseService.getTotalPages();
                    int currentPage3 = 1;
                    boolean continuePaging3 = true;
                    boolean isEdge3 = false;
                    while (continuePaging3) {
                        if (!isEdge3){
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage3 + "/" + totalPages3 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Course> courseList = courseService.SortCoursesByIdAsc(currentPage3);

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
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge3 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage3 < totalPages3) {
                                    currentPage3++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge3 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage3 > 1) {
                                    currentPage3--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge3 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages3 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages3) {
                                        currentPage3 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages3 + RESET);
                                        isEdge3 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge3 = true;
                                }
                                break;
                            case "0":
                                continuePaging3 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge3 = true;
                        }
                    }
                    break;
                case 4:
                    System.out.println(BOLD + GREEN + "→ Sắp xếp theo ID (giảm dần)" + RESET);
                    int totalPages4 = courseService.getTotalPages();
                    int currentPage4 = 1;
                    boolean continuePaging4 = true;
                    boolean isEdge4 = false;
                    while (continuePaging4) {
                        if (!isEdge4) {
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage4 + "/" + totalPages4 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Course> courseList = courseService.SortCoursesByIdDesc(currentPage4);

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
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge4 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage4 < totalPages4) {
                                    currentPage4++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage4 > 1) {
                                    currentPage4--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages4 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages4) {
                                        currentPage4 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages4 + RESET);
                                        isEdge4 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge4 = true;
                                }
                                break;
                            case "0":
                                continuePaging4 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge4 = true;
                        }
                    }
                    break;
                case 0:
                    System.out.println("Quay lại menu quản lý khóa học.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }while (true);
    }

    public static void showUpdateCourseMenu(Scanner scanner) {
        CourseService courseService = new CourseServiceImp();
        String courseId = CourseValidator.inputExistingCourseId("→ Nhập ID khóa học cần chỉnh sửa: ", scanner, courseService);

        Course course = courseService.getCourseById(courseId);

        if (course == null) {
            System.out.println("Không tìm thấy khóa học có ID: " + courseId);
            return;
        }
        System.out.println(YELLOW + "== THÔNG TIN KHÓA HỌC " + course.getCourseId() + " ==" + RESET);
        System.out.printf(GREEN + "%-15s" + RESET + ": " + BLUE + "%s\n" + RESET, "ID khóa học", course.getCourseId());
        System.out.printf(GREEN + "%-15s" + RESET + ": " + BLUE + "%s\n" + RESET, "Tên khóa học", course.getCourseName());
        System.out.printf(GREEN + "%-15s" + RESET + ": " + BLUE + "%d phút\n" + RESET, "Thời lượng", course.getDuration());
        System.out.printf(GREEN + "%-15s" + RESET + ": " + BLUE + "%s\n" + RESET, "Giảng viên", course.getInstructor());




        do {
            System.out.println("\n== CHỈNH SỬA KHÓA HỌC #" + courseId + " ==");
            System.out.println("1. Sửa tên khóa học");
            System.out.println("2. Sửa thời lượng");
            System.out.println("3. Sửa tên giảng viên");
            System.out.println("4. Lưu thay đổi");
            System.out.println("0. Hủy và quay lại");
            System.out.print("Chọn thuộc tính cần sửa: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    course.setCourseName(Validator.checkString("Nhập tên mới: ",scanner, 5, 100));
                    break;
                case "2":
                    course.setDuration((Validator.checkInt("Nhập thời lượng mới: ",scanner)));
                    break;
                case "3":
                    course.setInstructor(Validator.checkString("Nhập tên giảng viên mới: ", scanner, 5, 100));
                    break;
                case "4":
                    boolean checkConfirm = Validator.checkBoolean("Bạn có chắc chắn muốn lưu thay đổi không? (true/false): ", scanner);
                    if (checkConfirm) {
                        try {
                            courseService.updateCourse(course);
                            System.out.println("Cập nhật khóa học thành công!");
                        } catch (Exception e) {
                            System.out.println("Cập nhật khóa học thất bại: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Hủy cập nhật khóa học.");
                    }
                    return;
                case "0":
                    System.out.println("Hủy chỉnh sửa.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }while (true);
    }

    public static void searchCourseByName(Scanner scanner) {
        String courseName = Validator.checkString(BOLD + CYAN + "Nhập tên khóa học cần tìm kiếm:" + RESET, scanner, 0,100);
        int totalPages = courseService.getTotalSearchCourses(courseName);

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
                List<Course> courseList = courseService.GetCoursesBySearchNamePages(courseName, currentPage);

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

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ─────────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                         " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                          " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                         " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý khóa học" + RESET + "    " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "└────────────────────────────────────────┘" + RESET);
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

    public static void addCourse(Scanner scanner) {
        try {
            String courseId = CourseValidator.inputCourseId("Nhập ID khóa học (CXXXX):", scanner, courseService);
            String courseName = Validator.checkString("Nhập tên khóa học:", scanner, 5, 100);
            int duration = Validator.checkInt("Nhập thời lượng khóa học (phút):", scanner);
            String instructor = Validator.checkString("Nhập tên giảng viên:", scanner, 5, 100);
            Course newCourse = new Course();
            newCourse.setCourseId(courseId);
            newCourse.setCourseName(courseName);
            newCourse.setDuration(duration);
            newCourse.setInstructor(instructor);
            try {
                courseService.addCourse(newCourse);
                System.out.println("Thêm khóa học thành công!");
            } catch (Exception e) {
                System.out.println("Thêm khóa học thất bại: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    public static void deleteCourse(Scanner scanner) {
        String courseId = CourseValidator.inputExistingCourseId("Nhập ID khóa học cần xóa:", scanner, courseService);
        boolean checkConfirm = Validator.checkBoolean("Bạn có chắc chắn muốn xóa khóa học này không? (true/false): ", scanner);

        if (checkConfirm) {
            try {
                courseService.deleteCourse(courseId);
                System.out.println("Xóa khóa học thành công!");
            } catch (Exception e) {
                System.out.println("Xóa khóa học thất bại: " + e.getMessage());
            }
        } else {
            System.out.println("❎ Hủy xóa khóa học.");
        }
    }

    public static void paginatedStudent(Scanner scanner) {
        int totalPages = studentService.getTotalStudentPages();
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);

        int currentPage = 1;
        boolean continuePaging = true;
        boolean isEdge = false;

        while (continuePaging) {
            if (!isEdge) {
                System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                List<Student> studentList = studentService.getStudentsByPage(currentPage);

                if (studentList.isEmpty()) {
                    System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                } else {
                    System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                    System.out.println(BOLD + "╔════════╦═══════════════════════════╦═══════════════════════════╗" + RESET);
                    System.out.println(BOLD + "║   ID   ║           TÊN             ║          EMAIL            ║" + RESET);
                    System.out.println(BOLD + "╠════════╬═══════════════════════════╬═══════════════════════════╣" + RESET);

                    for (Student student : studentList) {
                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-25s " + RESET + BOLD + "║" + YELLOW + " %-25s " + RESET + BOLD + "║\n" + RESET,
                                student.getStudentId(), student.getName(), student.getEmail());
                    }

                    System.out.println(BOLD + "╚════════╩═══════════════════════════╩═══════════════════════════╝" + RESET);
                }
            }

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý sinh viên" + RESET + "  " + BOLD + PURPLE + " │" + RESET);
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
        return;
    }

    public static void addStudent(Scanner scanner) {
        System.out.println(BOLD + CYAN + "\n┌───────────────────────────────┐" + RESET);
        System.out.println(BOLD + CYAN + "│     THÊM SINH VIÊN MỚI        │" + RESET);
        System.out.println(BOLD + CYAN + "└───────────────────────────────┘" + RESET);

        String studentId = StudentValidator.inputNewStudentId(BOLD + YELLOW + "Nhập ID sinh viên (SXXXX):" + RESET, scanner, studentService);
        String name = Validator.checkString(BOLD + YELLOW + "Nhập tên sinh viên:" + RESET, scanner, 5, 100);
        String email = Validator.checkExistEmail(BOLD + YELLOW + "Nhập email sinh viên:" + RESET, scanner, studentService);
        String phoneNumber = Validator.checkPhone(BOLD + YELLOW + "Nhập số điện thoại sinh viên:" + RESET, scanner);
        Boolean gender = Validator.checkBoolean(BOLD + YELLOW + "Nhập giới tính sinh viên (true - nam/false - nữ):" + RESET, scanner);
        LocalDate dob = Validator.checkDate(BOLD + YELLOW + "Nhập ngày sinh sinh viên (dd/MM/yyyy):" + RESET, scanner);
        LocalDateTime createdDate = LocalDateTime.now();

        Student newStudent = new Student();
        newStudent.setStudentId(studentId);
        newStudent.setName(name);
        newStudent.setEmail(email);
        newStudent.setPhone(phoneNumber);
        newStudent.setGender(gender);
        newStudent.setDob(dob);
        newStudent.setCreatedAt(createdDate);

        try {
            studentService.addStudent(newStudent);
            System.out.println(BOLD + GREEN + "✓ Thêm sinh viên thành công!" + RESET);
        } catch (Exception e) {
            System.out.println(BOLD + RED + "✗ Thêm sinh viên thất bại: " + e.getMessage() + RESET);
        }
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.println(BOLD + RED + "\n┌───────────────────────────────┐" + RESET);
        System.out.println(BOLD + RED + "│        XÓA SINH VIÊN         │" + RESET);
        System.out.println(BOLD + RED + "└───────────────────────────────┘" + RESET);

        String studentId = StudentValidator.inputExistingStudentId(BOLD + YELLOW + "Nhập ID sinh viên cần xóa:" + RESET, scanner, studentService);

        System.out.println(BOLD + RED + "⚠ CẢNH BÁO: Thao tác này không thể hoàn tác!" + RESET);
        boolean checkConfirm = Validator.checkBoolean(BOLD + YELLOW + "Bạn có chắc chắn muốn xóa sinh viên này không? (true/false): " + RESET, scanner);

        if (checkConfirm) {
            try {
                studentService.deleteStudent(studentId);
                System.out.println(BOLD + GREEN + "✓ Xóa sinh viên thành công!" + RESET);
            } catch (Exception e) {
                System.out.println(BOLD + RED + "✗ Xóa sinh viên thất bại: " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(BOLD + BLUE + "ℹ Hủy xóa sinh viên." + RESET);
        }
    }

    public static void searchStudent(Scanner scanner) {
        System.out.println(BOLD + CYAN + "\n┌───────────────────────────────┐" + RESET);
        System.out.println(BOLD + CYAN + "│     TÌM KIẾM SINH VIÊN        │" + RESET);
        System.out.println(BOLD + CYAN + "└───────────────────────────────┘" + RESET);

        String studentName = Validator.checkString(BOLD + YELLOW + "Nhập tên sinh viên cần tìm kiếm:" + RESET, scanner, 0, 100);
        int totalPages = studentService.getSearchStudentsByPage(studentName);

        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);

        int currentPage = 1;
        boolean continuePaging = true;
        boolean isEdge = false;

        while (continuePaging) {
            if (!isEdge) {
                System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                List<Student> studentList = studentService.searchStudents(studentName, currentPage);

                if (studentList.isEmpty()) {
                    System.out.println(BOLD + RED + "Không có sinh viên nào phù hợp với tìm kiếm." + RESET);
                } else {
                    System.out.println(BOLD + CYAN + "❯❯❯ KẾT QUẢ TÌM KIẾM ❮❮❮" + RESET);
                    System.out.println(BOLD + "╔════════╦═══════════════════════════╦═══════════════════════════╗" + RESET);
                    System.out.println(BOLD + "║   ID   ║           TÊN             ║          EMAIL            ║" + RESET);
                    System.out.println(BOLD + "╠════════╬═══════════════════════════╬═══════════════════════════╣" + RESET);

                    for (Student student : studentList) {
                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-25s " + RESET + BOLD + "║" + YELLOW + " %-25s " + RESET + BOLD + "║\n" + RESET,
                                student.getStudentId(), student.getName(), student.getEmail());
                    }

                    System.out.println(BOLD + "╚════════╩═══════════════════════════╩═══════════════════════════╝" + RESET);
                }
            }

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý sinh viên" + RESET + "  " + BOLD + PURPLE + " │" + RESET);
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

    public static void showSortStudentMenu(Scanner scanner) {
        do {
            System.out.println(BOLD + CYAN + "\n══════════════════════════════" + RESET);
            System.out.println(BOLD + CYAN + "      SẮP XẾP SINH VIÊN      " + RESET);
            System.out.println(BOLD + CYAN + "══════════════════════════════" + RESET);
            System.out.println(BOLD + GREEN + "1. Theo tên (A → Z)" + RESET);
            System.out.println(BOLD + YELLOW + "2. Theo tên (Z → A)" + RESET);
            System.out.println(BOLD + BLUE + "3. Theo ID (tăng dần)" + RESET);
            System.out.println(BOLD + MAGENTA + "4. Theo ID (giảm dần)" + RESET);
            System.out.println(BOLD + RED + "0. Quay lại menu quản lý sinh viên" + RESET);
            System.out.print(BOLD + WHITE + "Chọn kiểu sắp xếp: " + RESET);
            int choice = Validator.checkInt("Chọn kiểu sắp xếp: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println(BOLD + GREEN + "→ Sắp xếp theo tên (A → Z)" + RESET);
                    int totalPages = studentService.getTotalStudentPages();
                    int currentPage = 1;
                    boolean continuePaging = true;
                    boolean isEdge = false;
                    while (continuePaging) {
                        if (!isEdge) {
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage + "/" + totalPages + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Student> studentList = studentService.SortStudentByNameAsc(currentPage);

                            if (studentList.isEmpty()) {
                                System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                            } else {
                                System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                                System.out.println(BOLD + "╔════════╦═══════════════════════╦══════════════════════════════╗" + RESET);
                                System.out.println(BOLD + "║   ID   ║         TÊN          ║            EMAIL             ║" + RESET);
                                System.out.println(BOLD + "╠════════╬═══════════════════════╬══════════════════════════════╣" + RESET);

                                for (Student student : studentList) {
                                    System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-21s " + RESET + BOLD + "║" + YELLOW + " %-28s " + RESET + BOLD + "║\n" + RESET,
                                            student.getStudentId(), student.getName(), student.getEmail());
                                }

                                System.out.println(BOLD + "╚════════╩═══════════════════════╩══════════════════════════════╝" + RESET);
                            }
                        }

                        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
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
                    break;
                case 2:
                    System.out.println(BOLD + YELLOW + "→ Sắp xếp theo tên (Z → A)" + RESET);
                    int totalPages2 = studentService.getTotalStudentPages();
                    int currentPage2 = 1;
                    boolean continuePaging2 = true;
                    boolean isEdge2 = false;
                    while (continuePaging2) {
                        if (!isEdge2) {
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage2 + "/" + totalPages2 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Student> studentList = studentService.SortStudentByNameDesc(currentPage2);

                            if (studentList.isEmpty()) {
                                System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                            } else {
                                System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                                System.out.println(BOLD + "╔════════╦═══════════════════════╦══════════════════════════════╗" + RESET);
                                System.out.println(BOLD + "║   ID   ║         TÊN          ║            EMAIL             ║" + RESET);
                                System.out.println(BOLD + "╠════════╬═══════════════════════╬══════════════════════════════╣" + RESET);

                                for (Student student : studentList) {
                                    System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-21s " + RESET + BOLD + "║" + YELLOW + " %-28s " + RESET + BOLD + "║\n" + RESET,
                                            student.getStudentId(), student.getName(), student.getEmail());
                                }

                                System.out.println(BOLD + "╚════════╩═══════════════════════╩══════════════════════════════╝" + RESET);
                            }
                        }

                        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge2 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage2 < totalPages2) {
                                    currentPage2++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage2 > 1) {
                                    currentPage2--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages2 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages2) {
                                        currentPage2 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages2 + RESET);
                                        isEdge2 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge2 = true;
                                }
                                break;
                            case "0":
                                continuePaging2 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge2 = true;
                        }
                    }
                    break;
                case 3:
                    System.out.println(BOLD + BLUE + "→ Sắp xếp theo ID (tăng dần)" + RESET);
                    int totalPages3 = studentService.getTotalStudentPages();
                    int currentPage3 = 1;
                    boolean continuePaging3 = true;
                    boolean isEdge3 = false;
                    while (continuePaging3) {
                        if (!isEdge3) {
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage3 + "/" + totalPages3 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Student> studentList = studentService.SortStudentByIdAsc(currentPage3);

                            if (studentList.isEmpty()) {
                                System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                            } else {
                                System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                                System.out.println(BOLD + "╔════════╦═══════════════════════╦══════════════════════════════╗" + RESET);
                                System.out.println(BOLD + "║   ID   ║         TÊN          ║            EMAIL             ║" + RESET);
                                System.out.println(BOLD + "╠════════╬═══════════════════════╬══════════════════════════════╣" + RESET);

                                for (Student student : studentList) {
                                    System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-21s " + RESET + BOLD + "║" + YELLOW + " %-28s " + RESET + BOLD + "║\n" + RESET,
                                            student.getStudentId(), student.getName(), student.getEmail());
                                }

                                System.out.println(BOLD + "╚════════╩═══════════════════════╩══════════════════════════════╝" + RESET);
                            }
                        }

                        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge3 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage3 < totalPages3) {
                                    currentPage3++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge3 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage3 > 1) {
                                    currentPage3--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge3 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages3 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages3) {
                                        currentPage3 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages3 + RESET);
                                        isEdge3 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge3 = true;
                                }
                                break;
                            case "0":
                                continuePaging3 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge3 = true;
                        }
                    }
                    break;
                case 4:
                    System.out.println(BOLD + MAGENTA + "→ Sắp xếp theo ID (giảm dần)" + RESET);
                    int totalPages4 = studentService.getTotalStudentPages();
                    int currentPage4 = 1;
                    boolean continuePaging4 = true;
                    boolean isEdge4 = false;
                    while (continuePaging4) {
                        if (!isEdge4) {
                            System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                            System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage4 + "/" + totalPages4 + RESET);
                            System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                            List<Student> studentList = studentService.SortStudentByIdDesc(currentPage4);

                            if (studentList.isEmpty()) {
                                System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                            } else {
                                System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                                System.out.println(BOLD + "╔════════╦═══════════════════════╦══════════════════════════════╗" + RESET);
                                System.out.println(BOLD + "║   ID   ║         TÊN          ║            EMAIL             ║" + RESET);
                                System.out.println(BOLD + "╠════════╬═══════════════════════╬══════════════════════════════╣" + RESET);

                                for (Student student : studentList) {
                                    System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-21s " + RESET + BOLD + "║" + YELLOW + " %-28s " + RESET + BOLD + "║\n" + RESET,
                                            student.getStudentId(), student.getName(), student.getEmail());
                                }

                                System.out.println(BOLD + "╚════════╩═══════════════════════╩══════════════════════════════╝" + RESET);
                            }
                        }

                        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET + "                      " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET + "                      " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu sắp xếp" + RESET + "           " + BOLD + PURPLE + " │" + RESET);
                        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
                        System.out.print(BOLD + WHITE + "Chọn chức năng: " + RESET);
                        String paginationChoice = scanner.nextLine();
                        isEdge4 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage4 < totalPages4) {
                                    currentPage4++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage4 > 1) {
                                    currentPage4--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print(BOLD + YELLOW + "Nhập số trang cần xem (1 đến " + totalPages4 + "): " + RESET);
                                try {
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages4) {
                                        currentPage4 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ! Vui lòng chọn từ 1 đến " + totalPages4 + RESET);
                                        isEdge4 = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ!" + RESET);
                                    isEdge4 = true;
                                }
                                break;
                            case "0":
                                continuePaging4 = false;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                                isEdge4 = true;
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
            }
        } while (true);
    }

    public static void displayStudentByEnrollmentList(Scanner scanner){
        int totalPages = courseService.getTotalPages();
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
                    System.out.println(BOLD + "╔════════╦═══════════════════════════════════╗" + RESET);
                    System.out.println(BOLD + "║   ID   ║              TÊN KHÓA HỌC         ║" + RESET);
                    System.out.println(BOLD + "╠════════╬═══════════════════════════════════╣" + RESET);

                    for (Course course : courseList) {
                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD +
                                        "║" + CYAN + " %-33s " + RESET + BOLD +
                                        "║\n" + RESET,
                                course.getCourseId(),
                                course.getCourseName());
                    }

                    System.out.println(BOLD + "╚════════╩═══════════════════════════════════╝" + RESET);


                }
            }

            System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Tiếp theo" + RESET +   "                      " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "2. Quay lại" + RESET +   "                       " + BOLD + PURPLE + "  │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "3. Chọn trang" + RESET +   "                      " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + YELLOW + "4. Chọn khóa học" + RESET +   "                   " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý khóa học" + RESET +  "  " + BOLD + PURPLE + " │" + RESET);
            System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
            int paginationChoice = Validator.checkInt(BOLD + WHITE + "Chọn chức năng: " + RESET,scanner);
//            scanner.nextLine();
            isEdge = false;

            switch (paginationChoice) {
                case 1:
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case 2:
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        isEdge = true;
                        continue;
                    }
                    break;
                case 3:
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
                case 4:
                    String courseId = CourseValidator.inputExistingCourseId("Nhập ID khóa học: ", scanner, courseService);
                    List<Student> studentList = enrollmentService.displayStudentByEnrollmentList(courseId);
                    if (studentList.isEmpty()) {
                        System.out.println(BOLD + RED + "Không có sinh viên nào." + RESET);
                    } else {
                        System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH SINH VIÊN ❮❮❮" + RESET);
                        System.out.println(BOLD + "╔════════╦═══════════════════════════╦══════════════╦══════════════════╦════════════════════════════════════╗" + RESET);
                        System.out.println(BOLD + "║   ID   ║           TÊN             ║     DOB      ║       SĐT        ║              EMAIL                 ║" + RESET);
                        System.out.println(BOLD + "╠════════╬═══════════════════════════╬══════════════╬══════════════════╬════════════════════════════════════╣" + RESET);
                        for (Student student : studentList) {
                            System.out.printf(BOLD + "║" + GREEN + " %-5s " + RESET + BOLD +
                                            " ║" + CYAN + " %-25s " + RESET + BOLD +
                                            "║" + YELLOW + " %-12s " + RESET + BOLD +
                                            "║" + MAGENTA + " %-16s " + RESET + BOLD +
                                            "║" + WHITE + " %-34s " + RESET + BOLD +
                                            "║\n" + RESET,
                                    student.getStudentId(),
                                    student.getName(),
                                    student.getDob(),
                                    student.getPhone(),
                                    student.getEmail());
                        }
                        System.out.println(BOLD + "╚════════╩═══════════════════════════╩══════════════╩══════════════════╩════════════════════════════════════╝" + RESET);
                    }
                    boolean backToCourseMenu = false;
                    while (!backToCourseMenu) {
                        System.out.println(BOLD + PURPLE + "\n┌─────────── MENU SAU KHI XEM SINH VIÊN ─────────┐" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Xem khóa học khác" + RESET + "                   " + BOLD + PURPLE + "        │" + RESET);
                        System.out.println(BOLD + PURPLE + "│" + RESET + " " + RED + "0. Quay lại menu quản lý khóa học" + RESET + "      " + BOLD + PURPLE + "        │" + RESET);
                        System.out.println(BOLD + PURPLE + "└────────────────────────────────────────────────┘" + RESET);
                        int choiceAfterView = Validator.checkInt(BOLD + WHITE + "Chọn chức năng: " + RESET, scanner);

                        switch (choiceAfterView) {
                            case 1:
                                backToCourseMenu = true;
                                break;
                            case 0:
                                continuePaging = false;
                                backToCourseMenu = true;
                                break;
                            default:
                                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                        }
                    }

                    break;


                case 0:
                    continuePaging = false;
                    break;
                default:
                    System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
                    isEdge = true;
            }
        }
    }

    public static void approveEnrollment(Scanner scanner){
        List<RegisteredCourseDTO> registeredCourseList = enrollmentService.getAllWaitingStatusEnrollment();
        if (registeredCourseList.isEmpty()) {
            System.out.println(BOLD + RED + "Không có yêu cầu nào đang chờ duyệt." + RESET);
            return;
        }
        System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH NHỮNG KHÓA HỌC CHƯA ĐĂNG KÍ ❮❮❮" + RESET);
        System.out.println(BOLD + "╔════════╦═══════════════════════════╦══════════════╦══════════════════╗" + RESET);
        System.out.println(BOLD + "║   ID   ║         TÊN KHÓA HỌC      ║     STATUS   ║     STUDENT_ID   ║" + RESET);
        System.out.println(BOLD + "╠════════╬═══════════════════════════╬══════════════╬══════════════════╬" + RESET);
        for (RegisteredCourseDTO registeredCourse : registeredCourseList) {
            System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD +
                            "║" + CYAN + " %-25s " + RESET + BOLD +
                            "║" + YELLOW + " %-12s " + RESET + BOLD +
                            "║" + MAGENTA + " %-16s " + RESET +
                            "║\n" + RESET,
                    registeredCourse.getCourseId(),
                    registeredCourse.getCourseName(),
                    registeredCourse.getStatus(),
                    registeredCourse.getStudentId());
        }
        System.out.println(BOLD + "╚════════╩═══════════════════════════╩══════════════╩══════════════════╝" + RESET);
        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Duyệt yêu cầu" + RESET + "                  " + BOLD + PURPLE + "  │" + RESET);
        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "0. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
        int choice = Validator.checkInt(BOLD + WHITE + "Chọn chức năng: " + RESET, scanner);
        switch (choice) {
            case 1:
                String courseId = CourseValidator.inputExistingCourseId("Nhập ID khóa học: ", scanner, courseService);
                String studentId = StudentValidator.inputExistingStudentId("Nhập ID sinh viên: ", scanner, studentService);
                enrollmentService.approveEnrollment(studentId,courseId);
                System.out.println(BOLD + GREEN + "Đã duyệt yêu cầu đăng ký khóa học thành công!" + RESET);
                break;
            case 0:
                return;
            default:
                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
        }
    }

    public static void deniedEnrollment(Scanner scanner){
        List<RegisteredCourseDTO> registeredCourseList = enrollmentService.getAllWaitingStatusEnrollment();
        if (registeredCourseList.isEmpty()) {
            System.out.println(BOLD + RED + "Không có yêu cầu nào đang chờ duyệt." + RESET);
            return;
        }
        System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH NHỮNG KHÓA HỌC CHƯA ĐĂNG KÍ ❮❮❮" + RESET);
        System.out.println(BOLD + "╔════════╦═══════════════════════════╦══════════════╦══════════════════╗" + RESET);
        System.out.println(BOLD + "║   ID   ║         TÊN KHÓA HỌC      ║     STATUS   ║     STUDENT_ID   ║" + RESET);
        System.out.println(BOLD + "╠════════╬═══════════════════════════╬══════════════╬══════════════════╬" + RESET);
        for (RegisteredCourseDTO registeredCourse : registeredCourseList) {
            System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD +
                            "║" + CYAN + " %-25s " + RESET + BOLD +
                            "║" + YELLOW + " %-12s " + RESET + BOLD +
                            "║" + MAGENTA + " %-16s " + RESET +
                            "║\n" + RESET,
                    registeredCourse.getCourseId(),
                    registeredCourse.getCourseName(),
                    registeredCourse.getStatus(),
                    registeredCourse.getStudentId());
        }
        System.out.println(BOLD + "╚════════╩═══════════════════════════╩══════════════╩══════════════════╝" + RESET);
        System.out.println(BOLD + PURPLE + "\n┌─────────── ĐIỀU HƯỚNG ──────────────┐" + RESET);
        System.out.println(BOLD + PURPLE + "│" + RESET + " " + GREEN + "1. Từ chối yêu cầu" + RESET + "                " + BOLD + PURPLE + "  │" + RESET);
        System.out.println(BOLD + PURPLE + "│" + RESET + " " + BLUE + "0. Quay lại" + RESET + "                       " + BOLD + PURPLE + "  │" + RESET);
        System.out.println(BOLD + PURPLE + "└─────────────────────────────────────┘" + RESET);
        int choice = Validator.checkInt(BOLD + WHITE + "Chọn chức năng: " + RESET, scanner);
        switch (choice) {
            case 1:
                String courseId = CourseValidator.inputExistingCourseId("Nhập ID khóa học: ", scanner, courseService);
                String studentId = StudentValidator.inputExistingStudentId("Nhập ID sinh viên: ", scanner, studentService);
                enrollmentService.deniedEnrollment(studentId,courseId);
                System.out.println(BOLD + GREEN + "Đã duyệt yêu cầu đăng ký khóa học thành công!" + RESET);
                break;
            case 0:
                return;
            default:
                System.out.println(BOLD + RED + "⚠ Lựa chọn không hợp lệ!" + RESET);
        }
    }

    public static void showStatisticsMenu(Scanner scanner){
        System.out.println(BOLD + CYAN + "== THỐNG KÊ KHÓA HỌC ==" + RESET);
        System.out.println("1. Danh sách sinh viên đăng kí theo từng khóa học");
        System.out.println("2. Duyệt");
        int choice = Validator.checkInt("Chọn chức năng: ", scanner);

        switch (choice) {
            case 1:
//                int totalCourses = courseService.getTotalCourses();
//                System.out.println("Tổng số khóa học: " + totalCourses);
                break;
            case 2:
//                int totalStudents = studentService.getTotalStudents();
//                System.out.println("Tổng số sinh viên: " + totalStudents);
                break;
            case 3:
//                int completedCourses = courseService.getCompletedCourses();
//                System.out.println("Tổng số khóa học đã hoàn thành: " + completedCourses);
                break;
            case 4:
//                int ongoingCourses = courseService.getOngoingCourses();
//                System.out.println("Tổng số khóa học đang diễn ra: " + ongoingCourses);
                break;
            case 0:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }
}