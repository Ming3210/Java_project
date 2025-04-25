package ra.edu.business.dao.enrollment;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.RegisteredEnrollmentDTO;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    @Override
    public List<Student> displayStudentByEnrollmentList( String courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL displayStudentByEnrollmentList( ?)}");
            callSt.setString(1, courseId);

            boolean hasResults = callSt.execute();
            if (hasResults) {
                rs = callSt.getResultSet();

                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString("student_id"));
                    student.setName(rs.getString("name"));
                    student.setDob(rs.getDate("dob").toLocalDate());
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                    studentList.add(student);
                }
            }
        } catch (Exception e) {
            System.err.println("Error displaying student by enrollment: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
            ConnectionDB.closeConnection(conn, callSt);
        }

        return studentList;
    }

    @Override
    public List<RegisteredEnrollmentDTO> getAllWaitingStatusEnrollment() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<RegisteredEnrollmentDTO> registeredCourseList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL getAllWaitingStatusEnrollment()}");
            boolean hasResults = callSt.execute();
            if (hasResults) {
                rs = callSt.getResultSet();
                while (rs.next()) {
                    RegisteredEnrollmentDTO registeredCourse = new RegisteredEnrollmentDTO();
                    registeredCourse.setStudentId(rs.getString("student_id"));
                    registeredCourse.setCourseId(rs.getString("course_id"));
                    registeredCourse.setCourseName(rs.getString("name"));
                    registeredCourse.setStatus(rs.getString("status"));
                    registeredCourseList.add(registeredCourse);
                }
            }
        } catch (Exception e) {
            System.err.println("Error getting all waiting status enrollment: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
            ConnectionDB.closeConnection(conn, callSt);
        }
        return registeredCourseList;
    }

    @Override
    public void approveEnrollment(String studentId, String courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL approveEnrollment(?, ?)}");
            callSt.setString(1, studentId);
            callSt.setString(2, courseId);
            callSt.execute();
        } catch (Exception e) {
            System.err.println("Error approving enrollment: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void deniedEnrollment(String studentId, String courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL deniedEnrollment(?, ?)}");
            callSt.setString(1, studentId);
            callSt.setString(2, courseId);
            callSt.execute();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<RegisteredEnrollmentDTO> sortRegistedEnrollmentByCondition(String studentId, String condition, String sortType, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<RegisteredEnrollmentDTO> registeredCourseList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL sortRegistedEnrollmentByCondition(?, ?, ?, ?)}");
            callSt.setString(1, studentId);
            callSt.setString(2, condition);
            callSt.setString(3, sortType);
            callSt.setInt(4, page);
            boolean hasResults = callSt.execute();
            if (hasResults) {
                rs = callSt.getResultSet();
                while (rs.next()) {
                    RegisteredEnrollmentDTO registeredCourse = new RegisteredEnrollmentDTO();
                    registeredCourse.setStudentId(rs.getString("student_id"));
                    registeredCourse.setCourseId(rs.getString("course_id"));
                    registeredCourse.setCourseName(rs.getString("name"));
                    registeredCourse.setStatus(rs.getString("status"));
                    registeredCourse.setCreatedAt(rs.getTimestamp("registered_at").toLocalDateTime());
                    registeredCourseList.add(registeredCourse);
                }
            }
        } catch (Exception e) {
            System.err.println("Error sorting registered enrollment by condition: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
            ConnectionDB.closeConnection(conn, callSt);
        }
        return registeredCourseList;
    }

}