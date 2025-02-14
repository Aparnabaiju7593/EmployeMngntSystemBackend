package com.OrganizationManagement.organizationManagement.Task;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task_table")
@Data
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private Long taskId;

    @Column(name = "deparmentId")
    private Long departmentId;

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "taskName")
    private String taskName;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
