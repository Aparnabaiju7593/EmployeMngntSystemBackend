package com.OrganizationManagement.organizationManagement.Designation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignationRepo extends JpaRepository<DesignationModel,Long> {

    Optional<DesignationModel> findByDesignationId(Long designationId);
}
