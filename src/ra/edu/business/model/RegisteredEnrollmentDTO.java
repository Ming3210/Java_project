package ra.edu.business.model;

public class RegisteredEnrollmentDTO {
    private String courseId;
    private String courseName;
    private String status;
    private String studentId;

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
