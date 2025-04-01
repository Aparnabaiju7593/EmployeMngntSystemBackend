package com.OrganizationManagement.organizationManagement.Late;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LateRepo extends JpaRepository<LateModel,Long> {
    List<LateModel> findByDepartmentId(Long departmentId);

    List<LateModel> findByEmployeeId(Long employeeId);
}
