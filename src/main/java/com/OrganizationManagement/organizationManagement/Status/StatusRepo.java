package com.OrganizationManagement.organizationManagement.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<StatusModel,Long> {
    List<StatusModel> findByStatusId(Long statusId);
}
