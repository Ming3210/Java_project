package ra.edu.business.service.course;


import ra.edu.business.dao.course.CourseDAO;
import ra.edu.business.dao.course.CourseDAOImp;
import ra.edu.business.dao.login.LoginDAO;
import ra.edu.business.dao.login.LoginDAOImp;
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


}
