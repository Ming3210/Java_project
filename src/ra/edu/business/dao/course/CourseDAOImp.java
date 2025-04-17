package ra.edu.business.dao.course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
