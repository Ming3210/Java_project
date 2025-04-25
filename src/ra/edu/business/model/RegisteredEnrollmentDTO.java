package ra.edu.business.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisteredEnrollmentDTO {
    private String courseId;
    private String courseName;
    private String status;
    private String studentId;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RegisteredEnrollmentDTO(String courseId, String courseName, LocalDateTime createdAt, String status, String studentId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.createdAt = createdAt;
        this.status = status;
        this.studentId = studentId;
    }

    public RegisteredEnrollmentDTO(String courseId, String courseName, String status, String studentId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
        this.studentId = studentId;
    }

    public RegisteredEnrollmentDTO(String courseId, String courseName, String status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public RegisteredEnrollmentDTO() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
