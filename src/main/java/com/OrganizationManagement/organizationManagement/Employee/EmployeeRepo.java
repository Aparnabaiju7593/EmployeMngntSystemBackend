package com.OrganizationManagement.organizationManagement.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel,Long> {
    Optional<EmployeeModel>findById(Long employeeId);

    Optional<EmployeeModel> findByEmailAndPassword(String email, String password);

    Optional<EmployeeModel> findByEmail(String email);
    // Optional<EmployeeModel> findByEmailAndPassword(String email, String password);
}
