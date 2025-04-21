package ra.edu.business.dao.student;

import ra.edu.business.model.RegisteredCourseDTO;
import ra.edu.business.model.Student;

import java.util.List;

public interface StudentDAO {
    int getTotalStudentPages();
    List<Student> getStudentsByPage(int page);
    void addStudent(Student student);
    boolean isStudentIdExists(String studentId);
    boolean checkEmailExist(String email);
//    void updateStudent(Student student);
    void deleteStudent(String studentId);
    List<Student> searchStudents(String searchTerm, int page);
    int getSearchStudentsByPage(String searchName);
    List<Student> SortStudentByNameAsc(int page);
    List<Student> SortStudentByNameDesc(int page);
    List<Student> SortStudentByIdAsc(int page);
    List<Student> SortStudentByIdDesc(int page);
    Student getStudentByEmail(String email);
    void registerEnrollment(String studentId, String courseId);
    List<RegisteredCourseDTO> showAllRegistedEnrollment(String studentId);
    void cancelEnrollment(String studentId, String courseId);
    boolean checkOldPassword(String studentId, String password);
    void updatePassword(String studentId, String password);
    int getTotalRegistedEnrollmentPages(String studentId);
    List<RegisteredCourseDTO> getRegistedEnrollmentByPage(String studentId, int page);
}
