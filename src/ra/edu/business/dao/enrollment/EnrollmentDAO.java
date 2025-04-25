package ra.edu.business.dao.enrollment;

import ra.edu.business.model.RegisteredEnrollmentDTO;
import ra.edu.business.model.Student;

import java.util.List;

public interface EnrollmentDAO {
    List<Student> displayStudentByEnrollmentList( String courseId);
    List<RegisteredEnrollmentDTO> getAllWaitingStatusEnrollment();
    void approveEnrollment(String studentId, String courseId);
    void deniedEnrollment(String studentId, String courseId);
}
