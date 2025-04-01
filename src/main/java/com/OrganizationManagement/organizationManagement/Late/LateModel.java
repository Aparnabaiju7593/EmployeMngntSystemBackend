package com.OrganizationManagement.organizationManagement.Late;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin
@Entity
@Table(name = "lateTable")
@Data
public class LateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateId")
    private Long lateId;

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "lateDate")
    private LocalDate lateDate;

    @Column(name = "reason")
    private String reason;


    @Column(name = "statusId")
    private Long statusId;


    @Column(name = "submittedTime")
    private LocalDateTime submittedTime;

    public Long getLateId() {
        return lateId;
    }

    public void setLateId(Long lateId) {
        this.lateId = lateId;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public LateModel(){
        this.statusId=1L;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getLateDate() {
        return lateDate;
    }

    public void setLateDate(LocalDate lateDate) {
        this.lateDate = lateDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
