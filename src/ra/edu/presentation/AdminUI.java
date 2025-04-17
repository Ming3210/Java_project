package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.CourseValidator;

import java.util.List;
import java.util.Scanner;

public class AdminUI {
    static CourseService courseService = new CourseServiceImp();
    public static void showAdminMenu(Scanner scanner) {

        do {
            System.out.println("\n===== MENU ADMIN – QUẢN LÝ KHÓA HỌC =====");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học theo ID");
            System.out.println("5. Tìm kiếm khóa học theo tên");
            System.out.println("6. Sắp xếp khóa học");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("→ Hiển thị danh sách khóa học");
                    paginatedCourse(scanner);
                    break;
                case "2":
                    System.out.println("→ Thêm mới khóa học");
                    addCourse(scanner);
                    break;
                case "3":
                    showUpdateCourseMenu(scanner);
                    break;
                case "4":
                    System.out.println("→ Xóa khóa học");
                    break;
                case "5":
                    System.out.println("→ Tìm kiếm khóa học theo tên");
                    break;
                case "6":
                    showSortCourseMenu(scanner);
                    break;
                case "0":
                    System.out.println("Đăng xuất thành công!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }while (true);
    }


    public static void showSortCourseMenu(Scanner scanner) {
       do {
           System.out.println("\n== SẮP XẾP KHÓA HỌC ==");
           System.out.println("1. Theo tên (A → Z)");
           System.out.println("2. Theo tên (Z → A)");
           System.out.println("3. Theo ID (tăng dần)");
           System.out.println("4. Theo ID (giảm dần)");
              System.out.println("0. Quay lại menu chính");
           System.out.print("Chọn kiểu sắp xếp: ");
           String choice = scanner.nextLine();

           System.out.println("→ Danh sách đã sắp xếp:");
              switch (choice) {
                case "1":
                     System.out.println("→ Sắp xếp theo tên (A → Z)");
                     break;
                case "2":
                     System.out.println("→ Sắp xếp theo tên (Z → A)");
                     break;
                case "3":
                     System.out.println("→ Sắp xếp theo ID (tăng dần)");
                     break;
                case "4":
                     System.out.println("→ Sắp xếp theo ID (giảm dần)");
                     break;
                case "0":
                    System.out.println("Quay lại menu chính.");
                    return;
                default:
                     System.out.println("Lựa chọn không hợp lệ.");
              }
       }while (true);
    }


    public static void showUpdateCourseMenu(Scanner scanner) {
        CourseService courseService = new CourseServiceImp();
        System.out.println("→ Nhập ID khóa học cần chỉnh sửa: ");
        String courseId = scanner.nextLine();

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học có ID: " + courseId);
            return;
        }

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
                    course.setCourseName(CourseValidator.checkString("Nhập tên mới: ",scanner, 5, 100));
                    break;
                case "2":
                    course.setDuration((CourseValidator.checkInt("Nhập thời lượng mới: ",scanner)));
                    break;
                case "3":
                    course.setInstructor(CourseValidator.checkString("Nhập tên giảng viên mới: ", scanner, 5, 100));
                    break;
                case "4":
                    boolean checkConfirm = CourseValidator.checkBoolean("Bạn có chắc chắn muốn lưu thay đổi không? (true/false): ", scanner);
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


    public static void paginatedCourse(Scanner scanner){
        int totalPages = courseService.getTotalPages();
        System.out.println("Tổng số trang: " + totalPages);

        int currentPage = 1;
        boolean continuePaging = true;

        while (continuePaging) {
            System.out.println("\nTrang hiện tại: " + currentPage);
            List<Course> courseList = courseService.getCoursesByPage(currentPage);

            if (courseList.isEmpty()) {
                System.out.println("Không có khóa học nào.");
            } else {
                System.out.println("Danh sách khóa học:");
                for (Course course : courseList) {
                    System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() +" phút");
                }
            }

            System.out.println("\n1. Tiếp theo");
            System.out.println("2. Quay lại");
            System.out.println("3. Chọn trang");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");
            String paginationChoice = scanner.nextLine();

            switch (paginationChoice) {
                case "1":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("Đã ở trang cuối cùng.");
                        continue;
                    }
                    break;
                case "2":
                    if (currentPage > 1) {
                        currentPage--;

                    } else {
                        System.out.println("Đã ở trang đầu tiên.");
                        continue;
                    }
                    break;
                case "3":
                    System.out.print("Nhập số trang cần xem (1 đến " + totalPages + "): ");
                    int selectedPage = Integer.parseInt(scanner.nextLine());
                    if (selectedPage >= 1 && selectedPage <= totalPages) {
                        currentPage = selectedPage;
                    } else {
                        System.out.println("Số trang không hợp lệ.");
                    }
                    break;
                case "0":
                    continuePaging = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    public static void addCourse(Scanner scanner) {
        try {
            String courseId = CourseValidator.inputCourseId("Nhập ID khóa học:", scanner, courseService);
            String courseName = CourseValidator.checkString("Nhập tên khóa học:", scanner, 5, 100);
            int duration = CourseValidator.checkInt("Nhập thời lượng khóa học (phút):", scanner);
            String instructor = CourseValidator.checkString("Nhập tên giảng viên:", scanner, 5, 100);
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


}
