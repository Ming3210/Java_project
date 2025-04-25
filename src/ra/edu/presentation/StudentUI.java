package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.model.RegisteredEnrollmentDTO;
import ra.edu.business.model.Session;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentService;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;


public class StudentUI {
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
    public static final String MAGENTA = "\u001B[38;5;214m";

    public static void showStudentMenu(Scanner scanner) {
        while (true) {
            System.out.println(BLUE + BOLD + "╔══════════════════════════════════════╗" + RESET);
            System.out.println(BLUE + BOLD + "║            MENU SINH VIÊN            ║" + RESET);
            System.out.println(BLUE + BOLD + "╠══════════════════════════════════════╣" + RESET);
            System.out.println(BLUE + "║ 1. Xem danh sách khóa học            ║" + RESET);
            System.out.println(BLUE + "║ 2. Đăng ký khóa học                  ║" + RESET);
            System.out.println(BLUE + "║ 3. Xem khóa học đã đăng ký           ║" + RESET);
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


    public static void paginatedCourse(Scanner scanner) {
        System.out.println(BOLD +CYAN +"======== MENU KHÓA HỌC ĐÃ ĐĂNG KÍ ========");
        System.out.println("1. Xem tất cả khóa học");
        System.out.println("2. Tìm kiếm khóa học theo tên");
        System.out.println("0. Quay lại");
        System.out.println(BOLD +CYAN +"========================================" + RESET);
        int choice = Validator.checkInt(CYAN + "Chọn chức năng: " + RESET, scanner);
        switch (choice) {
            case 1:
                int totalPages = courseService.getTotalPages();
                System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
                System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);

                int currentPage = 1;

                while (true) {


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

                    for (int i = 1; i <= totalPages; i++) {
                        if (i == currentPage) {
                            System.out.printf(BOLD + YELLOW + "[%d]   " + RESET, i);
                        } else {
                            System.out.printf(MAGENTA + "%d   " + RESET, i);
                        }
                    }
                    System.out.println();

                    System.out.print(BOLD + YELLOW + "\nNhập số trang, 'n' để tiếp theo, 'p' để quay lại, '0' để thoát: " + RESET);
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("n")) {
                        if (currentPage < totalPages) {
                            currentPage++;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        }
                    } else if (input.equalsIgnoreCase("p")) {
                        if (currentPage > 1) {
                            currentPage--;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        }
                    } else if (input.equals("0")) {
                        break;
                    } else {
                        try {
                            int selectedPage = Integer.parseInt(input);
                            if (selectedPage >= 1 && selectedPage <= totalPages) {
                                currentPage = selectedPage;
                            } else {
                                System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ!" + RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ hoặc 'n', 'p', '0'!" + RESET);
                        }
                    }
                }
                break;
            case 2:
                System.out.println(GREEN + "🔍 Tìm kiếm khóa học theo tên" + RESET);
                String courseName = Validator.checkString(BOLD + CYAN + "Nhập tên khóa học cần tìm kiếm:" + RESET, scanner, 0,100);
                int totalPages2 = courseService.getTotalSearchCourses(courseName);

                System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
                System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages2 + RESET);
                System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);

                int currentPage2 = 1;
                boolean continuePaging2 = true;
                boolean isEdge2 = false;

                while (continuePaging2) {
                    if (!isEdge2){
                        System.out.println(BOLD + BLUE + "\n═════════════════════════════════════" + RESET);
                        System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage2 + "/" + totalPages2 + RESET);
                        System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                        List<Course> courseList = courseService.GetCoursesBySearchNamePages(courseName, currentPage2);

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

                    for (int i = 1; i <= totalPages2; i++) {
                        if (i == currentPage2) {
                            System.out.printf(BOLD + YELLOW + "[%d]   " + RESET, i);
                        } else {
                            System.out.printf(MAGENTA + "%d   " + RESET, i);
                        }
                    }
                    System.out.println();

                    System.out.print(BOLD + YELLOW + "\nNhập số trang, 'n' để tiếp theo, 'p' để quay lại, '0' để thoát: " + RESET);
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("n")) {
                        if (currentPage2 < totalPages2) {
                            currentPage2++;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        }
                    } else if (input.equalsIgnoreCase("p")) {
                        if (currentPage2 > 1) {
                            currentPage2--;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        }
                    } else if (input.equals("0")) {
                        break;
                    } else {
                        try {
                            int selectedPage = Integer.parseInt(input);
                            if (selectedPage >= 1 && selectedPage <= totalPages2) {
                                currentPage2 = selectedPage;
                            } else {
                                System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ!" + RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ hoặc 'n', 'p', '0'!" + RESET);
                        }
                    }
                }
                break;
            case 0:
                return;
            default:
                System.out.println(RED + "⚠ Lựa chọn không hợp lệ." + RESET);
        }
    }


    public static void registerEnrollment(Scanner scanner){
        int totalPages = courseService.getTotalPages();
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalPages + RESET);
        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
        int currentPage = 1;
        while (true) {


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

            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
                    System.out.printf(BOLD + YELLOW + "[%d]   " + RESET, i);
                } else {
                    System.out.printf(MAGENTA + "%d   " + RESET, i);
                }
            }
            System.out.println();

            System.out.print(BOLD + YELLOW + "\nNhập số trang, 'n' để tiếp theo, 'p' để quay lại, 'e' để nhập Id khóa học , '0' để thoát: " + RESET);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("n")) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                }
            } else if (input.equalsIgnoreCase("p")) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                }
            }else if(input.equals("e")){
                String courseId = CourseValidator.inputExistingCourseId("→ Nhập ID khóa học muốn đăng kí: ", scanner, courseService);
                String studentId = Session.currentStudent.getStudentId();
                try {
                    studentService.registerEnrollment(studentId, courseId);
                    System.out.println(GREEN + "Đăng ký khóa học thành công!" + RESET);
                } catch (Exception e) {
                    System.err.println(RED  + e.getMessage() + RESET);
                }
            }

            else if (input.equals("0")) {
                break;
            } else {
                try {
                    int selectedPage = Integer.parseInt(input);
                    if (selectedPage >= 1 && selectedPage <= totalPages) {
                        currentPage = selectedPage;
                    } else {
                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ!" + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ hoặc 'n', 'p', '0'!" + RESET);
                }
            }
        }


    }

    public static void showAllRegistedEnrollment(Scanner scanner){
        System.out.println(BOLD +CYAN +"======== MENU KHÓA HỌC ĐÃ ĐĂNG KÍ ========");
        System.out.println("1. Xem tất cả khóa học đã đăng ký");
        System.out.println("2. Sắp xếp khóa học theo tên hoặc ngày đăng kí");
        System.out.println("0. Quay lại");
        System.out.println(BOLD +CYAN +"========================================" + RESET);
        int choice = Validator.checkInt(CYAN + "Chọn chức năng: " + RESET, scanner);
        switch (choice) {
            case 1:
                System.out.println(GREEN + "📜 Xem tất cả khóa học đã đăng ký" + RESET);
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
                        List<RegisteredEnrollmentDTO> registeredEnrollment = studentService.getRegistedEnrollmentByPage(studentId, currentPage);
                        if (registeredEnrollment.isEmpty()){
                            System.out.println(BOLD + RED + "Không có khóa học nào." + RESET);
                        } else {
                            System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH KHÓA HỌC ĐÃ ĐĂNG KÝ ❮❮❮" + RESET);
                            System.out.println(BOLD + "╔════════╦═══════════════════════════╦═══════════════╗" + RESET);
                            System.out.println(BOLD + "║   ID   ║         TÊN KHÓA HỌC      ║ STATUS        ║" + RESET);
                            System.out.println(BOLD + "╠════════╬═══════════════════════════╬═══════════════╣" + RESET);

                            for (RegisteredEnrollmentDTO enrollment : registeredEnrollment) {
                                System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-25s "  +
                                                RESET+ BOLD +"║"+ YELLOW +" %-13s "+RESET+ BOLD +"║"+ BOLD+"\n" +RESET,
                                        enrollment.getCourseId(), enrollment.getCourseName(), enrollment.getStatus());
                            }

                            System.out.println(BOLD+"╚════════╩═══════════════════════════╩═══════════════╝"+RESET);

                        }
                    }

                    for (int i = 1; i <= totalPages; i++) {
                        if (i == currentPage) {
                            System.out.printf(BOLD + YELLOW + "[%d]   " + RESET, i);
                        } else {
                            System.out.printf(MAGENTA + "%d   " + RESET, i);
                        }
                    }
                    System.out.println();

                    System.out.print(BOLD + YELLOW + "\nNhập số trang, 'n' để tiếp theo, 'p' để quay lại, '0' để thoát: " + RESET);
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("n")) {
                        if (currentPage < totalPages) {
                            currentPage++;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                        }
                    } else if (input.equalsIgnoreCase("p")) {
                        if (currentPage > 1) {
                            currentPage--;
                        } else {
                            System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                        }
                    } else if (input.equals("0")) {
                        break;
                    } else {
                        try {
                            int selectedPage = Integer.parseInt(input);
                            if (selectedPage >= 1 && selectedPage <= totalPages) {
                                currentPage = selectedPage;
                            } else {
                                System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ!" + RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ hoặc 'n', 'p', '0'!" + RESET);
                        }
                    }
                }
                break;
            case 2:
                String studentId2 = Session.currentStudent.getStudentId();
                int totalsPages2 = studentService.getTotalRegistedEnrollmentPages(studentId2);
                System.out.println(GREEN + "🔍 Sắp xếp khóa học đã đăng kí" + RESET);
                String sortType = "";
                String choice1 = Validator.checkString(BOLD + CYAN + "Sắp xếp theo (name/date):" + RESET, scanner, 0,100);
                switch (choice1){
                    case "name":
                        sortType = Validator.checkString(BOLD + CYAN + "Chọn thứ tự (asc/desc):" + RESET, scanner, 0, 10);
                        while (!sortType.equalsIgnoreCase("asc") && !sortType.equalsIgnoreCase("desc")) {
                            System.out.println(RED + "Lựa chọn không hợp lệ! Vui lòng nhập 'asc' hoặc 'desc'" + RESET);
                            sortType = Validator.checkString(BOLD + CYAN + "Chọn thứ tự (asc/desc):" + RESET, scanner, 0, 10);
                        }
                        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
                        System.out.println(BOLD + BLUE + "   Tổng số trang: " + RESET + YELLOW + totalsPages2 + RESET);
                        System.out.println(BOLD + BLUE + "════════════════════════════════════════" + RESET);
                        int currentPage2 = 1;
                        boolean continuePaging2 = true;
                        boolean isEdge2 = false;
                        while (continuePaging2){
                            if (!isEdge2){
                                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                                System.out.println(BOLD + BLUE + "   Trang hiện tại: " + RESET + YELLOW + currentPage2 + "/" + totalsPages2 + RESET);
                                System.out.println(BOLD + BLUE + "═════════════════════════════════════" + RESET);
                                List<RegisteredEnrollmentDTO> registeredEnrollment = enrollmentService.sortRegistedEnrollmentByCondition(studentId2,choice1,sortType,totalsPages2);
                                if (registeredEnrollment.isEmpty()){
                                    System.out.println(BOLD + RED + "Không có khóa học nào." + RESET);
                                } else {
                                    System.out.println(BOLD + CYAN + "❯❯❯ DANH SÁCH KHÓA HỌC ĐÃ ĐĂNG KÝ ❮❮❮" + RESET);
                                    System.out.println(BOLD + "╔════════╦═══════════════════════════╦═════════════════╦════════════════════════╗" + RESET);
                                    System.out.println(BOLD + "║   ID   ║         TÊN KHÓA HỌC      ║ STATUS          ║ CREATE AT              ║" + RESET);
                                    System.out.println(BOLD + "╠════════╬═══════════════════════════╬═════════════════╬════════════════════════╣" + RESET);

                                    for (RegisteredEnrollmentDTO enrollment : registeredEnrollment) {
                                        System.out.printf(BOLD + "║" + GREEN + " %-6s " + RESET + BOLD + "║" + CYAN + " %-25s "  +
                                                        RESET+ BOLD +"║"+ YELLOW +" %-15s "+RESET+ BOLD +"║"+ BOLD+MAGENTA +" %-20s "+RESET+ BOLD +"  ║"+ BOLD+  "\n" +RESET,
                                                enrollment.getCourseId(), enrollment.getCourseName(), enrollment.getStatus(), enrollment.getCreatedAt());
                                    }

                                    System.out.println(BOLD+"╚════════╩═══════════════════════════╩═════════════════╩════════════════════════╝"+RESET);

                                }
                            }

                            for (int i = 1; i <= totalsPages2; i++) {
                                if (i == currentPage2) {
                                    System.out.printf(BOLD + YELLOW + "[%d]   " + RESET, i);
                                } else {
                                    System.out.printf(MAGENTA + "%d   " + RESET, i);
                                }
                            }
                            System.out.println();

                            System.out.print(BOLD + YELLOW + "\nNhập số trang, 'n' để tiếp theo, 'p' để quay lại, '0' để thoát: " + RESET);
                            String input = scanner.nextLine().trim();

                            if (input.equalsIgnoreCase("n")) {
                                if (currentPage2 < totalsPages2) {
                                    currentPage2++;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang cuối cùng!" + RESET);
                                }
                            } else if (input.equalsIgnoreCase("p")) {
                                if (currentPage2 > 1) {
                                    currentPage2--;
                                } else {
                                    System.out.println(BOLD + RED + "⚠ Đã ở trang đầu tiên!" + RESET);
                                }
                            } else if (input.equals("0")) {
                                break;
                            } else {
                                try {
                                    int selectedPage = Integer.parseInt(input);
                                    if (selectedPage >= 1 && selectedPage <= totalsPages2) {
                                        currentPage2 = selectedPage;
                                    } else {
                                        System.out.println(BOLD + RED + "⚠ Số trang không hợp lệ!" + RESET);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(BOLD + RED + "⚠ Vui lòng nhập một số hợp lệ hoặc 'n', 'p', '0'!" + RESET);
                                }
                            }
                        }
                }
                break;
            case 0:
                return;
            default:
                System.out.println(RED + "⚠ Lựa chọn không hợp lệ." + RESET);
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
        String email = Validator.checkEmail(PURPLE+"→ Nhập email: "+RESET, scanner);
        while (!email.equalsIgnoreCase(Session.currentStudent.getEmail())){
            System.out.println(RED + "Email không đúng!" + RESET);
            email = Validator.checkEmail(PURPLE+"→ Nhập email: "+RESET, scanner);
        }
        String oldPassword = Validator.checkString("→ Nhập mật khẩu cũ: ", scanner, 6, 20);
        boolean isDuplicate = false;
        boolean validPassword =  studentService.checkOldPassword(studentId, oldPassword);
        while (!validPassword){
            oldPassword = Validator.checkString("→ Nhập mật khẩu cũ: ", scanner, 6, 20);
            validPassword = studentService.checkOldPassword(studentId, oldPassword);
        }
        String newPassword = Validator.checkString("→ Nhập mật khẩu mới: ", scanner, 6, 20);
        while (!isDuplicate){
            if(newPassword.equals(oldPassword)){
                isDuplicate = true;
            }
            System.out.println(RED + "Mật khẩu mới không được trùng với mật khẩu cũ!" + RESET);
            newPassword = Validator.checkString("→ Nhập mật khẩu mới: ", scanner, 6, 20);

        }
        String confirmPassword = Validator.checkString("→ Nhập lại mật khẩu mới: ", scanner, 6, 20);

        if (newPassword.equals(confirmPassword)) {
            studentService.updatePassword(studentId, newPassword);
            System.out.println(GREEN + "Cập nhật mật khẩu thành công!" + RESET);
        } else {
            System.out.println(RED + "Mật khẩu xác nhận không khớp!" + RESET);
        }


    }
}
