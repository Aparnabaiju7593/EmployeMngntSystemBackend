package com.OrganizationManagement.organizationManagement.ReqResource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReqResourceRepo extends JpaRepository<ReqResourceModel,Long> {
    List<ReqResourceModel> findByEmployeeId(Long employeeId); // Returns all matching records
}
