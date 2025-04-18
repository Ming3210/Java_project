package ra.edu.business.dao.student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;
import ra.edu.business.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO{
    @Override
    public int getTotalStudentPages() {
        int totalPages = 0;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL getTotalStudentPages()}");
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                totalPages = rs.getInt("total_pages");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalPages;
    }

    @Override
    public List<Student> getStudentsByPage(int page) {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL getStudentsByPage(?)}");
            callSt.setInt(1, page);

            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return students;
    }

    @Override
    public void addStudent(Student student, String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL AddNewStudent(?, ?, ?, ?, ?, ?, ?, ?)}");

            callSt.setString(1, student.getStudentId());
            callSt.setString(2, student.getName());
            callSt.setDate(3, Date.valueOf(student.getDob()));
            callSt.setString(4, student.getEmail());
            callSt.setBoolean(5, student.isGender());
            callSt.setString(6, student.getPhone());
            callSt.setString(7, username);
            callSt.setString(8, password);

            callSt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean isStudentIdExists(String studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL isStudentExists(?)}");
            callSt.setString(1, studentId);
            rs = callSt.executeQuery();
            if (rs.next()) {
                exists = rs.getBoolean("is_exists");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra tồn tại sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return exists;
    }

//    @Override
//    public void updateStudent(Student student) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{CALL updateStudent(?, ?, ?, ?, ?, ?)}");
//
//            callSt.setString(1, student.getStudentId());
//            callSt.setString(2, student.getName());
//            callSt.setDate(3, Date.valueOf(student.getDob()));
//            callSt.setString(4, student.getEmail());
//            callSt.setBoolean(5, student.isGender());
//            callSt.setString(6, student.getPhone());
//
//            int rowsAffected = callSt.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Cập nhật sinh viên thành công!");
//            } else {
//                System.out.println("Không tìm thấy sinh viên với ID: " + student.getStudentId());
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi cập nhật sinh viên: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//    }

    @Override
    public void deleteStudent(String studentId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL deleteStudent(?)}");
            callSt.setString(1, studentId);

            callSt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi xoá sinh viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }




    @Override
    public int getSearchStudentsByPage(String searchName) {
        int totalPages = 0;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL getSearchStudentsByPage(?)}");
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
    public List<Student> SortStudentByNameAsc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortStudentByNameAsc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> SortStudentByNameDesc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortStudentByNameDesc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> SortStudentByIdAsc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortStudentByIdAsc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> SortStudentByIdDesc(int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL SortStudentByIdDesc(?)}");
            callSt.setInt(1, page);
            rs = callSt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> searchStudents(String searchTerm, int page) {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL searchStudents(?, ?)}");
            callSt.setString(1, searchTerm);
            callSt.setInt(2, page);

            rs = callSt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setGender(rs.getBoolean("gender"));
                student.setPhone(rs.getString("phone"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm học viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return students;
    }



}
