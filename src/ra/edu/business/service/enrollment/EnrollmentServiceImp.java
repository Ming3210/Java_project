package ra.edu.business.service.enrollment;


import ra.edu.business.dao.enrollment.EnrollmentDAO;
import ra.edu.business.dao.enrollment.EnrollmentDAOImp;
import ra.edu.business.model.RegisteredEnrollmentDTO;
import ra.edu.business.model.Student;

import java.util.List;

public class EnrollmentServiceImp implements EnrollmentService{
    private final EnrollmentDAO enrollmentDAO;


    public EnrollmentServiceImp() {
        enrollmentDAO = new EnrollmentDAOImp();
    }

    @Override
    public List<Student> displayStudentByEnrollmentList(String courseId) {
        return enrollmentDAO.displayStudentByEnrollmentList(courseId);
    }

    @Override
    public List<RegisteredEnrollmentDTO> getAllWaitingStatusEnrollment() {
        return enrollmentDAO.getAllWaitingStatusEnrollment();
    }

    @Override
    public void approveEnrollment(String studentId, String courseId) {
        enrollmentDAO.approveEnrollment(studentId, courseId);
    }

    @Override
    public void deniedEnrollment(String studentId, String courseId) {
        enrollmentDAO.deniedEnrollment(studentId, courseId);
    }
}
