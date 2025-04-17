package ra.edu.business.model;

import java.time.LocalDate;

public class Course {
    private String courseId;
    private String courseName;
    private int duration;
    private String instructor;
    private LocalDate createdAt;

    public Course(String courseId, String courseName, LocalDate createdAt, int duration, String instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.createdAt = createdAt;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Course() {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
