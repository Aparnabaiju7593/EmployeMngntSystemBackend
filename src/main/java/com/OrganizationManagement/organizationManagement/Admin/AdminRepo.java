package com.OrganizationManagement.organizationManagement.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<AdminModel,Long> {
    Optional<AdminModel> findByEmailAndPassword(String email, String password);

    Optional<AdminModel> findByEmail(String email);
}
