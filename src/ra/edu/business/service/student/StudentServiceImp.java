package ra.edu.business.service.student;





import ra.edu.business.dao.student.StudentDAO;
import ra.edu.business.dao.student.StudentDAOImp;
import ra.edu.business.model.Student;

import java.util.List;

public class StudentServiceImp implements StudentService{
    private final StudentDAO studentDAO;


    public StudentServiceImp() {
        studentDAO = new StudentDAOImp();
    }


    @Override
    public int getTotalStudentPages() {
        return studentDAO.getTotalStudentPages();
    }

    @Override
    public List<Student> getStudentsByPage(int page) {
        return studentDAO.getStudentsByPage(page);
    }

    @Override
    public void addStudent(Student student, String username, String password) {
        studentDAO.addStudent(student, username, password);
    }

    @Override
    public boolean isStudentIdExists(String studentId) {
        return studentDAO.isStudentIdExists(studentId);
    }

//    @Override
//    public void updateStudent(Student student) {
//        studentDAO.updateStudent(student);
//    }

    @Override
    public void deleteStudent(String studentId) {
        studentDAO.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudents(String searchTerm, int page) {
        return studentDAO.searchStudents(searchTerm, page);
    }

    @Override
    public int getSearchStudentsByPage(String searchName) {
        return studentDAO.getSearchStudentsByPage(searchName);
    }

    @Override
    public List<Student> SortStudentByNameAsc(int page) {
        return studentDAO.SortStudentByNameAsc(page);
    }

    @Override
    public List<Student> SortStudentByNameDesc(int page) {
        return studentDAO.SortStudentByNameDesc(page);
    }

    @Override
    public List<Student> SortStudentByIdAsc(int page) {
        return studentDAO.SortStudentByIdAsc(page);
    }

    @Override
    public List<Student> SortStudentByIdDesc(int page) {
        return studentDAO.SortStudentByIdDesc(page);
    }
}
