package com.OrganizationManagement.organizationManagement.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin

@AllArgsConstructor
@NoArgsConstructor
public class LateDto {
    private Long lateId;
    private Long departmentId;
    private String department;
    private Long employeeId;
    private String employeeName;
    private LocalDate lateDate;
    private String reason;
    private String status;
    private LocalDateTime submittedTime;




    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getLateDate() {
        return lateDate;
    }

    public void setLateDate(LocalDate lateDate) {
        this.lateDate = lateDate;
    }

    public Long getLateId() {
        return lateId;
    }

    public void setLateId(Long lateId) {
        this.lateId = lateId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
