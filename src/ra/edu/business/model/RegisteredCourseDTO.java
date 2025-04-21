package ra.edu.business.model;

public class RegisteredCourseDTO {
    private String courseId;
    private String courseName;
    private String status;

    public RegisteredCourseDTO(String courseId, String courseName, String status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public RegisteredCourseDTO() {
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
