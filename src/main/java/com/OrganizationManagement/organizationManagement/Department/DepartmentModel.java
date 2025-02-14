package com.OrganizationManagement.organizationManagement.Department;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "department_table")
@Data
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depId")
    private Long depId;

    @Column(name = "department")
    private String department;

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
