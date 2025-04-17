package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

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
            int choice = Validator.checkInt("Chọn chức năng: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("→ Hiển thị danh sách khóa học");
                    paginatedCourse(scanner);
                    break;
                case 2:
                    System.out.println("→ Thêm mới khóa học");
                    addCourse(scanner);
                    break;
                case 3:
                    showUpdateCourseMenu(scanner);
                    break;
                case 4:
                    System.out.println("→ Xóa khóa học");
                    deleteCourse(scanner);
                    break;
                case 5:
                    System.out.println("→ Tìm kiếm khóa học theo tên");
                    searchCourseByName(scanner);
                    break;
                case 6:
                    showSortCourseMenu(scanner);
                    break;
                case 0:
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
           int choice = Validator.checkInt("Chọn kiểu sắp xếp: ", scanner);

           System.out.println("→ Danh sách đã sắp xếp:");
              switch (choice) {
                case 1:
                     System.out.println("→ Sắp xếp theo tên (A → Z)");
                     int totalPages = courseService.getTotalPages();
                     int currentPage = 1;
                     boolean continuePaging = true;
                     boolean isEdge = false;
                        while (continuePaging) {
                            if (!isEdge){
                                System.out.println("\nTrang hiện tại: " + currentPage);
                                List<Course> courseList = courseService.SortCoursesByNameAsc(currentPage);

                                if (courseList.isEmpty()) {
                                    System.out.println("Không có khóa học nào.");
                                } else {
                                    System.out.println("Danh sách khóa học:");
                                    for (Course course : courseList) {
                                        System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() +" phút");
                                    }
                                }
                            }

                            System.out.println("\n1. Tiếp theo");
                            System.out.println("2. Quay lại");
                            System.out.println("3. Chọn trang");
                            System.out.println("0. Quay lại menu chính");
                            System.out.print("Chọn chức năng: ");
                            String paginationChoice = scanner.nextLine();
                            isEdge = false;

                            switch (paginationChoice) {
                                case "1":
                                    if (currentPage < totalPages) {
                                        currentPage++;
                                    } else {
                                        System.out.println("Đã ở trang cuối cùng.");
                                        isEdge = true;
                                        continue;
                                    }
                                    break;
                                case "2":
                                    if (currentPage > 1) {
                                        currentPage--;

                                    } else {
                                        System.out.println("Đã ở trang đầu tiên.");
                                        isEdge = true;
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
                     break;
                case 2:
                    System.out.println("→ Sắp xếp theo tên (Z → A)");
                    int totalPages2 = courseService.getTotalPages();
                    int currentPage2 = 1;
                    boolean continuePaging2 = true;
                    boolean isEdge2 = false;
                    while (continuePaging2) {
                        if (!isEdge2){
                            System.out.println("\nTrang hiện tại: " + currentPage2);
                            List<Course> courseList = courseService.SortCoursesByNameDesc(currentPage2);

                            if (courseList.isEmpty()) {
                                System.out.println("Không có khóa học nào.");
                            } else {
                                System.out.println("Danh sách khóa học:");
                                for (Course course : courseList) {
                                    System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() +" phút");
                                }
                            }
                        }

                        System.out.println("\n1. Tiếp theo");
                        System.out.println("2. Quay lại");
                        System.out.println("3. Chọn trang");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("Chọn chức năng: ");
                        String paginationChoice = scanner.nextLine();
                        isEdge2 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage2 < totalPages2) {
                                    currentPage2++;
                                } else {
                                    System.out.println("Đã ở trang cuối cùng.");
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage2 > 1) {
                                    currentPage2--;

                                } else {
                                    System.out.println("Đã ở trang đầu tiên.");
                                    isEdge2 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print("Nhập số trang cần xem (1 đến " + totalPages2 + "): ");
                                int selectedPage = Integer.parseInt(scanner.nextLine());
                                if (selectedPage >= 1 && selectedPage <= totalPages2) {
                                    currentPage2 = selectedPage;
                                } else {
                                    System.out.println("Số trang không hợp lệ.");
                                }
                                break;
                            case "0":
                                continuePaging2 = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                    }
                     break;
                case 3:
                     System.out.println("→ Sắp xếp theo ID (tăng dần)");
                        int totalPages3 = courseService.getTotalPages();
                        int currentPage3 = 1;
                        boolean continuePaging3 = true;
                        boolean isEdge3 = false;
                        while (continuePaging3) {
                            if (!isEdge3){
                                System.out.println("\nTrang hiện tại: " + currentPage3);
                                List<Course> courseList = courseService.SortCoursesByIdAsc(currentPage3);

                                if (courseList.isEmpty()) {
                                    System.out.println("Không có khóa học nào.");
                                } else {
                                    System.out.println("Danh sách khóa học:");
                                    for (Course course : courseList) {
                                        System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() +" phút");
                                    }
                                }
                            }

                            System.out.println("\n1. Tiếp theo");
                            System.out.println("2. Quay lại");
                            System.out.println("3. Chọn trang");
                            System.out.println("0. Quay lại menu chính");
                            System.out.print("Chọn chức năng: ");
                            String paginationChoice = scanner.nextLine();
                            isEdge3 = false;

                            switch (paginationChoice) {
                                case "1":
                                    if (currentPage3 < totalPages3) {
                                        currentPage3++;
                                    } else {
                                        System.out.println("Đã ở trang cuối cùng.");
                                        isEdge3 = true;
                                        continue;
                                    }
                                    break;
                                case "2":
                                    if (currentPage3 > 1) {
                                        currentPage3--;

                                    } else {
                                        System.out.println("Đã ở trang đầu tiên.");
                                        isEdge3 = true;
                                        continue;
                                    }
                                    break;
                                case "3":
                                    System.out.print("Nhập số trang cần xem (1 đến " + totalPages3 + "): ");
                                    int selectedPage = Integer.parseInt(scanner.nextLine());
                                    if (selectedPage >= 1 && selectedPage <= totalPages3) {
                                        currentPage3 = selectedPage;
                                    } else {
                                        System.out.println("Số trang không hợp lệ.");
                                    }
                                    break;
                                case "0":
                                    continuePaging3 = false;
                                    break;
                                default:
                                    System.out.println("Lựa chọn không hợp lệ.");
                            }
                        }
                     break;
                case 4:
                     System.out.println("→ Sắp xếp theo ID (giảm dần)");
                    int totalPages4 = courseService.getTotalPages();
                    int currentPage4 = 1;
                    boolean continuePaging4 = true;
                    boolean isEdge4 = false;
                    while (continuePaging4) {
                        if (!isEdge4) {
                            System.out.println("\nTrang hiện tại: " + currentPage4);
                            List<Course> courseList = courseService.SortCoursesByIdDesc(currentPage4);

                            if (courseList.isEmpty()) {
                                System.out.println("Không có khóa học nào.");
                            } else {
                                System.out.println("Danh sách khóa học:");
                                for (Course course : courseList) {
                                    System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() + " phút");
                                }
                            }
                        }

                        System.out.println("\n1. Tiếp theo");
                        System.out.println("2. Quay lại");
                        System.out.println("3. Chọn trang");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("Chọn chức năng: ");
                        String paginationChoice = scanner.nextLine();
                        isEdge4 = false;

                        switch (paginationChoice) {
                            case "1":
                                if (currentPage4 < totalPages4) {
                                    currentPage4++;
                                } else {
                                    System.out.println("Đã ở trang cuối cùng.");
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "2":
                                if (currentPage4 > 1) {
                                    currentPage4--;

                                } else {
                                    System.out.println("Đã ở trang đầu tiên.");
                                    isEdge4 = true;
                                    continue;
                                }
                                break;
                            case "3":
                                System.out.print("Nhập số trang cần xem (1 đến " + totalPages4 + "): ");
                                int selectedPage = Integer.parseInt(scanner.nextLine());
                                if (selectedPage >= 1 && selectedPage <= totalPages4) {
                                    currentPage4 = selectedPage;
                                } else {
                                    System.out.println("Số trang không hợp lệ.");
                                }
                                break;
                            case "0":
                                continuePaging4 = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                    }

                     break;
                case 0:
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
        String courseName = Validator.checkString("Nhập tên khóa học cần tìm kiếm:", scanner, 0, 100);
        int totalPages = courseService.getTotalSearchCourses(courseName);
        System.out.println("Tổng số trang: " + totalPages);
        int currentPage = 1;
        boolean continuePaging = true;

        boolean isEdge = false;

        while (continuePaging) {
            if (!isEdge){
                System.out.println("\nTrang hiện tại: " + currentPage);
                List<Course> courseList = courseService.GetCoursesBySearchNamePages(courseName,currentPage);

                if (courseList.isEmpty()) {
                    System.out.println("Không có khóa học nào.");
                } else {
                    System.out.println("Danh sách khóa học:");
                    for (Course course : courseList) {
                        System.out.println("ID: " + course.getCourseId() + " - Tên: " + course.getCourseName() + " - Thời lượng: " + course.getDuration() +" phút");
                    }
                }
            }

            System.out.println("\n1. Tiếp theo");
            System.out.println("2. Quay lại");
            System.out.println("3. Chọn trang");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");
            String paginationChoice = scanner.nextLine();
            isEdge = false;

            switch (paginationChoice) {
                case "1":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("Đã ở trang cuối cùng.");
                        isEdge = true;
                        continue;
                    }
                    break;
                case "2":
                    if (currentPage > 1) {
                        currentPage--;

                    } else {
                        System.out.println("Đã ở trang đầu tiên.");
                        isEdge = true;
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

    public static void paginatedCourse(Scanner scanner){
        int totalPages = courseService.getTotalPages();
        System.out.println("Tổng số trang: " + totalPages);

        int currentPage = 1;
        boolean continuePaging = true;
        boolean isEdge = false;

        while (continuePaging) {
            if (!isEdge){
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
            }

            System.out.println("\n1. Tiếp theo");
            System.out.println("2. Quay lại");
            System.out.println("3. Chọn trang");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");
            String paginationChoice = scanner.nextLine();
            isEdge = false;

            switch (paginationChoice) {
                case "1":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("Đã ở trang cuối cùng.");
                        isEdge = true;
                        continue;
                    }
                    break;
                case "2":
                    if (currentPage > 1) {
                        currentPage--;

                    } else {
                        System.out.println("Đã ở trang đầu tiên.");
                        isEdge = true;
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


}
