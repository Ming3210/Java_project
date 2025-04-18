package ra.edu.business.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private String studentId;
    private String name;
    private LocalDate dob;
    private String email;
    private boolean gender;
    private String phone;
    private LocalDateTime createdAt;

    public Student(LocalDateTime createdAt, LocalDate dob, String email, boolean gender, String name, String phone, String studentId) {
        this.createdAt = createdAt;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.studentId = studentId;
    }

    public Student() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
