package com.OrganizationManagement.organizationManagement.Hr;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Hr_table")
@Data

public class HrModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hrId")
    private Long hrId;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "hrName")
    private String hrName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Long getHrId() {
        return hrId;
    }

    public void setHrId(Long hrId) {
        this.hrId = hrId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}