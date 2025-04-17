package ra.edu.business.dao.course;

import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDAO {
    int getTotalPages();
    List<Course> getCoursesByPage(int page);
    boolean isCourseIdExists(String courseId);
    void addCourse(Course course);
}
