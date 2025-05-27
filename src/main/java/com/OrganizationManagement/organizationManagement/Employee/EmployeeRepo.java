package com.OrganizationManagement.organizationManagement.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel,Long> {
    Optional<EmployeeModel>findById(Long employeeId);

    Optional<EmployeeModel> findByEmailAndPassword(String email, String password);

    Optional<EmployeeModel> findByEmail(String email);

    List<EmployeeModel> findByDesignationId(Long designationId);

    List<EmployeeModel> findByRoleId(long i);
    // Optional<EmployeeModel> findByEmailAndPassword(String email, String password);
}
