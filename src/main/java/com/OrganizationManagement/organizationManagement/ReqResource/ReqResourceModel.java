package com.OrganizationManagement.organizationManagement.ReqResource;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reqResource_table")

public class ReqResourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reqResourceId")
    private Long reqResourceId;

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "resourceId")
    private Long resourceId;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "quantity")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    @Column(name = "statusId")
    private Long statusId;

    @Column(name = "requestDate")
    private LocalDate requestDate;

    @Column(name = "ApprovalDate")
    private LocalDateTime approvalDate;

    @Column(name = "remarks")
    private String remarks;


    public ReqResourceModel() {
        this.statusId = 1L;

    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getReqResourceId() {
        return reqResourceId;
    }

    public void setReqResourceId(Long reqResourceId) {
        this.reqResourceId = reqResourceId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }
}