package ra.edu.validate;

import ra.edu.business.service.course.CourseService;

import java.util.Scanner;

public class CourseValidator {


    public static String inputCourseId(String message, Scanner scanner, CourseService courseService) {
        System.out.println(message);
        boolean isValidId = false;
        String id = null;

        while (!isValidId) {
            try {
                id = scanner.nextLine();

                if (!id.matches("^C\\w{4}$")) {
                    throw new Exception("Id bắt đầu bằng chữ \"C\" và kết hợp với 4 kí tự số phía sau");
                }

                if (courseService.isCourseIdExists(id)) {
                    throw new Exception("Id khóa học đã tồn tại trong cơ sở dữ liệu");
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
                if (!id.matches("^C\\w{4}$")) {
                    throw new Exception("Id phải bắt đầu bằng chữ 'C' và theo sau là 4 ký tự.");
                }

                if (!courseService.isCourseIdExists(id)) {
                    throw new Exception("Id khóa học không tồn tại.");
                }

                return id;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
