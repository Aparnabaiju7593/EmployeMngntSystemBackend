package com.OrganizationManagement.organizationManagement.Leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepo extends JpaRepository<LeaveModel,Long> {
  //  List<LeaveModel> findAllByEmployeeId();

    List<LeaveModel> findByEmployeeId(Long employeeId);

    List<LeaveModel> findByDepartmentId(Long departmentId);

}
