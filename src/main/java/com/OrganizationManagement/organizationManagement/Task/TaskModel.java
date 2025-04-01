package com.OrganizationManagement.organizationManagement.Task;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "progressTym")
    private LocalTime progressTym;

    @Column(name = "completeTym")
    private LocalTime completeTym;

    @Column(name = "statusId")
    private Long statusId;

    public TaskModel(){
        this.statusId=1L;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalTime getProgressTym() {
        return progressTym;
    }

    public void setProgressTym(LocalTime progressTym) {
        this.progressTym = progressTym;
    }

    public LocalTime getCompleteTym() {
        return completeTym;
    }

    public void setCompleteTym(LocalTime completeTym) {
        this.completeTym = completeTym;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

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
