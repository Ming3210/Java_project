package ra.edu.business.service.enrollment;

import ra.edu.business.model.RegisteredCourseDTO;
import ra.edu.business.model.Student;

import java.util.List;

public interface EnrollmentService {
    List<Student> displayStudentByEnrollmentList( String courseId);
    List<RegisteredCourseDTO> getAllWaitingStatusEnrollment();
    void approveEnrollment(String studentId, String courseId);
    void deniedEnrollment(String studentId, String courseId);

}
