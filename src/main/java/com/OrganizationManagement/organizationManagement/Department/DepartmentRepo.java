package com.OrganizationManagement.organizationManagement.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentModel,Long> {
    List<DepartmentModel> findByDepartmentId(Long departmentId);
}
