package com.OrganizationManagement.organizationManagement.Employee;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "employee_table")
@Data
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "designationId")
    private Long designationId;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phnno")
    private Long phnno;

    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "joinDate")
    private LocalDate joinDate;

    @Lob
    @Column(name = "employeeImage")
    private byte[] employeeImage;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public byte[] getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(byte[] employeeImage) {
        this.employeeImage = employeeImage;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Long getPhnno() {
        return phnno;
    }

    public void setPhnno(Long phnno) {
        this.phnno = phnno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

}
