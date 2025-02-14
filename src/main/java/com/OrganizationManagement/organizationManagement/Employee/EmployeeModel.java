package com.OrganizationManagement.organizationManagement.Employee;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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

    public EmployeeModel(){
        this.roleId=2L;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

//    @Column(name = "joinDate")
//    private LocalDate joinDate;
//
//    @Lob
//    @Column(name = "employeeImage")
//    private byte[] employeeImage;

//    @Column(name = "joinDate")
//    private LocalDate joinDate;

    public byte[] getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(byte[] employeeImage) {
        this.employeeImage = employeeImage;
    }
//    public byte getEmployeeImage() {
//        return employeeImage;
//    }

//    public void setEmployeeImage(byte employeeImage) {
//        this.employeeImage = employeeImage;
//    }

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

   
//    public Long getRoleId() {
//    }
}
