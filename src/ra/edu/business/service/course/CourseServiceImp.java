package ra.edu.business.service.course;


import ra.edu.business.dao.course.CourseDAO;
import ra.edu.business.dao.course.CourseDAOImp;
import ra.edu.business.model.Course;

import java.util.List;

public class CourseServiceImp implements CourseService{

    private final CourseDAO courseDAO;


    public CourseServiceImp() {
        courseDAO = new CourseDAOImp();
    }



    @Override
    public int getTotalPages() {
        return courseDAO.getTotalPages();
    }

    @Override
    public List<Course> getCoursesByPage(int pageNumber) {
        return courseDAO.getCoursesByPage(pageNumber);
    }

    @Override
    public boolean isCourseIdExists(String courseId) {
        return courseDAO.isCourseIdExists(courseId);
    }

    @Override
    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public void deleteCourse(String courseId) {
        courseDAO.deleteCourse(courseId);
    }

    @Override
    public int getTotalSearchCourses(String searchName) {
        return courseDAO.getTotalSearchCourses(searchName);
    }

    @Override
    public List<Course> GetCoursesBySearchNamePages(String searchName, int pageSize) {
        return courseDAO.GetCoursesBySearchNamePages(searchName, pageSize);
    }

    @Override
    public List<Course> SortCoursesByNameAsc(int page) {
        return courseDAO.SortCoursesByNameAsc(page);
    }

    @Override
    public List<Course> SortCoursesByNameDesc(int page) {
        return courseDAO.SortCoursesByNameDesc(page);
    }

    @Override
    public List<Course> SortCoursesByIdAsc(int page) {
        return courseDAO.SortCoursesByIdAsc(page);
    }


    @Override
    public List<Course> SortCoursesByIdDesc(int page) {
        return courseDAO.SortCoursesByIdDesc(page);
    }


}
