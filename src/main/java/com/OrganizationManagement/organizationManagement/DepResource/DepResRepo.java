package com.OrganizationManagement.organizationManagement.DepResource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepResRepo extends JpaRepository<DepResModel,Long> {
}
