package ra.edu.business.dao.course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO{
    @Override
    public int getTotalPages() {
        int totalPages = 0;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_total_pages()}");

            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                totalPages = rs.getInt("total_pages");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return totalPages;
    }

    @Override
    public List<Course> getCoursesByPage(int page) {
        List<Course> courses = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_courses_by_page(?)}");
            callSt.setInt(1, page);

            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("course_id"));
                course.setCourseName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreatedAt(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return courses;
    }

    @Override
    public boolean isCourseIdExists(String courseId) {
        boolean exists = false;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL CheckCourseIdExists(?, ?)}");
            callSt.setString(1, courseId);
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();

            exists = callSt.getBoolean(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return exists;
    }

    @Override
    public void addCourse(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL add_course(?, ?, ?, ?)}");

            callSt.setString(1, course.getCourseId());
            callSt.setString(2, course.getCourseName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());

            callSt.execute();

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

}
