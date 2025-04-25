package ra.edu.business.dao.course;

import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDAO {
    int getTotalPages();
    List<Course> getCoursesByPage(int page);
    boolean isCourseIdExists(String courseId);
    boolean isCourseNameExists(String courseName);

    void addCourse(Course course);
    void updateCourse(Course course);
    Course getCourseById(String courseId);
    void deleteCourse(String courseId);
    void DeleteCourseSort(String courseId);
    int getTotalSearchCourses(String searchName);
    List<Course> GetCoursesBySearchNamePages(String searchName, int pageSize);
    List<Course> SortCoursesByNameAsc(int page);
    List<Course> SortCoursesByNameDesc(int page);
    List<Course> SortCoursesByIdAsc(int page);
    List<Course> SortCoursesByIdDesc(int page);
}
