package com.OrganizationManagement.organizationManagement.Late;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
@CrossOrigin
@Entity
@Table(name = "lateTable")
@Data
public class LateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "lateDate")
    private LocalDate lateDate;

    @Column(name = "reason")
    private String reason;

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
