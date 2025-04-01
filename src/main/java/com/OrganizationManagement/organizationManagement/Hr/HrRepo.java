package com.OrganizationManagement.organizationManagement.Hr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HrRepo extends JpaRepository<HrModel,Long> {

    Optional<HrModel> findByEmailAndPassword(String email, String password);
}
