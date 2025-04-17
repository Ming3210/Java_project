package ra.edu.business.service.course;

import ra.edu.business.model.Course;

import java.util.List;

public interface CourseService {
    int getTotalPages();
    List<Course> getCoursesByPage(int pageSize);
    boolean isCourseIdExists(String courseId);
    void addCourse(Course course);
    void updateCourse(Course course);
    Course getCourseById(String courseId);
}
