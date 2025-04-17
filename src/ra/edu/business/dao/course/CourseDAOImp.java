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

            callSt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void updateCourse(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL UpdateCourse(?, ?, ?, ?)}");
            callSt.setString(1, course.getCourseId());
            callSt.setString(2, course.getCourseName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());
            callSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }


    @Override
    public Course getCourseById(String courseId) {
        Course course = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL GetCourseById(?)}");
            callSt.setString(1, courseId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setCourseId(rs.getString("course_id"));
                course.setCourseName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreatedAt(rs.getDate("create_at").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public void deleteCourse(String courseId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL DeleteCourse(?)}");
            callSt.setString(1, courseId);
            callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public int getTotalSearchCourses(String searchName) {
        int totalPages = 0;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL GetTotalSearchCoursePages(?)}");
            callSt.setString(1, searchName);
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
    public List<Course> GetCoursesBySearchNamePages(String searchName, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL GetCoursesBySearchNamePages(?, ?)}");
            callSt.setString(1, searchName);
            callSt.setInt(2, pageSize);
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
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> SortCoursesByNameAsc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortCoursesByNameAsc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
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
    public List<Course> SortCoursesByNameDesc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortCoursesByNameDesc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
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
    public List<Course> SortCoursesByIdAsc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortCoursesByIdAsc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
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
    public List<Course> SortCoursesByIdDesc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortCoursesByIdDesc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
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
