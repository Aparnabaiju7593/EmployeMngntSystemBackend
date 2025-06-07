package com.OrganizationManagement.organizationManagement.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskModel,Long> {

    List<TaskModel> findByEmployeeId(Long employeeId);


    List<TaskModel> findByDepartmentId(Long departmentId);
}
