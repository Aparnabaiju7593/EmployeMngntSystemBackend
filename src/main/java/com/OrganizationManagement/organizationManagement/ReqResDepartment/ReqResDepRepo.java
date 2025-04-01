package com.OrganizationManagement.organizationManagement.ReqResDepartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqResDepRepo extends JpaRepository<ReqResDepModel,Long> {
}
