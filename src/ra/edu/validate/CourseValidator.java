package ra.edu.validate;

import ra.edu.business.service.course.CourseService;

import java.util.Scanner;

public class CourseValidator {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static String inputCourseId(String message, Scanner scanner, CourseService courseService) {
        System.out.println(message);
        boolean isValidId = false;
        String id = null;

        while (!isValidId) {
            try {
                id = scanner.nextLine();

                if (id.isEmpty()) {
                    throw new Exception(RED + "Id không được để trống" + RESET);
                }

                if (!id.matches("^C\\w{4}$")) {
                    throw new Exception(RED + "Id bắt đầu bằng chữ \"C\" và kết hợp với 4 kí tự số phía sau" + RESET);
                }

                if (courseService.isCourseIdExists(id)) {
                    throw new Exception(RED + "Id khóa học đã tồn tại trong cơ sở dữ liệu" + RESET);
                }

                isValidId = true;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return id;
    }

    public static String inputExistingCourseId(String message, Scanner scanner, CourseService courseService) {
        System.out.println(message);
        while (true) {
            try {
                String id = scanner.nextLine();

                if (id.isEmpty()) {
                    throw new Exception(RED + "Id không được để trống" + RESET);
                }

                if (!id.matches("^C\\w{4}$")) {
                    throw new Exception(RED + "Id phải bắt đầu bằng chữ 'C' và theo sau là 4 ký tự." + RESET);
                }

                if (!courseService.isCourseIdExists(id)) {
                    throw new Exception(RED+"Id khóa học không tồn tại."+RESET);
                }

                return id;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static String inputCourseName(String message, Scanner scanner, CourseService courseService, String courseName) {
        System.out.println(message);
        while (true) {
            try {
                String name = scanner.nextLine();
                if(name.equals(courseName)){
                    return name;
                }

                if (name.isEmpty()) {
                    throw new Exception(RED + "Tên khóa học không được để trống" + RESET);
                }
                if (!name.equals(name.trim())) {
                    throw new Exception(RED + "Không được có khoảng trắng ở đầu hoặc cuối chuỗi" + RESET);
                }

                if (name.matches(".*\\s{2,}.*")) {
                    throw new Exception(RED + "Không được có nhiều hơn một khoảng trắng giữa các từ" + RESET);
                }
                if(courseService.isCourseNameExists(name)) {
                    throw new Exception(String.format(RED + "Tên %s đã tồn tại trong cơ sở dữ liệu" + RESET, name));
                }
                return name;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }
    public static String inputCourseNameNew(String message, Scanner scanner, CourseService courseService) {
        System.out.println(message);
        while (true) {
            try {
                String name = scanner.nextLine();

                if (name.isEmpty()) {
                    throw new Exception(RED + "Tên khóa học không được để trống" + RESET);
                }
                if (!name.equals(name.trim())) {
                    throw new Exception(RED + "Không được có khoảng trắng ở đầu hoặc cuối chuỗi" + RESET);
                }

                if (name.matches(".*\\s{2,}.*")) {
                    throw new Exception(RED + "Không được có nhiều hơn một khoảng trắng giữa các từ" + RESET);
                }
                if (courseService.isCourseNameExists(name)) {
                    throw new Exception(String.format(RED + "Tên %s đã tồn tại trong cơ sở dữ liệu" + RESET, name));
                }
                return name;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
