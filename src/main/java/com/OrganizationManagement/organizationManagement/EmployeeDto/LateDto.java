package com.OrganizationManagement.organizationManagement.Admin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class LateDto {
    private Long departmentId;
    private Long employeeId;
    private LocalDate lateDate;
    private String reason;


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
