package ra.edu.business.service.student;


import ra.edu.business.dao.student.StudentDAO;
import ra.edu.business.dao.student.StudentDAOImp;
import ra.edu.business.model.RegisteredCourseDTO;
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
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    @Override
    public boolean isStudentIdExists(String studentId) {
        return studentDAO.isStudentIdExists(studentId);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return studentDAO.checkEmailExist(email);
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

    @Override
    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    @Override
    public void registerEnrollment(String studentId, String courseId) {
        studentDAO.registerEnrollment(studentId, courseId);
    }

    @Override
    public List<RegisteredCourseDTO> showAllRegistedEnrollment(String studentId) {
        return studentDAO.showAllRegistedEnrollment(studentId);
    }

    @Override
    public void cancelEnrollment(String studentId, String courseId) {
        studentDAO.cancelEnrollment(studentId, courseId);
    }

    @Override
    public boolean checkOldPassword(String studentId, String password) {
        return studentDAO.checkOldPassword(studentId, password);
    }

    @Override
    public void updatePassword(String studentId, String password) {
        studentDAO.updatePassword(studentId, password);
    }

    @Override
    public int getTotalRegistedEnrollmentPages(String studentId) {
        return studentDAO.getTotalRegistedEnrollmentPages(studentId);
    }

    @Override
    public List<RegisteredCourseDTO> getRegistedEnrollmentByPage(String studentId, int page) {
        return studentDAO.getRegistedEnrollmentByPage(studentId,page);
    }


}
