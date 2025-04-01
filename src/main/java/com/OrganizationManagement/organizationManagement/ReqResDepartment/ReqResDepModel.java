package com.OrganizationManagement.organizationManagement.ReqResDepartment;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ReqResDep_table")
public class ReqResDepModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reqresDepId")
    private Long reqresDepId;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "depresourceId")
    private Long depresourceId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "statusId")
    private Long statusId;

    @Column(name = "requestedDate")
    private LocalDate requestedDate;

    @Column(name = "approvedDate")
    private LocalDateTime approvedDate;

    public ReqResDepModel(){
        this.statusId = 1L;
    }

    public ReqResDepModel(Long statusId) {
        this.statusId = statusId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepresourceId() {
        return depresourceId;
    }

    public void setDepresourceId(Long depresourceId) {
        this.depresourceId = depresourceId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }
}
