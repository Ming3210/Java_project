package ra.edu.business.service.student;


import ra.edu.business.model.Student;

import java.util.List;

public interface StudentService {
    int getTotalStudentPages();
    List<Student> getStudentsByPage(int page);
    void addStudent(Student student, String username, String password);
    boolean isStudentIdExists(String studentId);
//    void updateStudent(Student student);
    void deleteStudent(String studentId);
    List<Student> searchStudents(String searchTerm, int page);
    int getSearchStudentsByPage(String searchName);
    List<Student> SortStudentByNameAsc(int page);
    List<Student> SortStudentByNameDesc(int page);
    List<Student> SortStudentByIdAsc(int page);
    List<Student> SortStudentByIdDesc(int page);
}
