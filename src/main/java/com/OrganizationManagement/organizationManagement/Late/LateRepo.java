package com.OrganizationManagement.organizationManagement.Late;

import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LateRepo extends JpaRepository<LateModel,Long> {
    List<LateModel> findByDepartmentId(Long departmentId);

    List<LateModel> findByEmployeeId(Long employeeId);
    // Add this method to your LeaveRepository interface
    @Query("SELECT l FROM LeaveModel l WHERE l.employeeId = :employeeId AND " +
            ":lateDate BETWEEN l.startDate AND l.endDate")
    List<LeaveModel> findByEmployeeIdAndDate(@Param("employeeId") Long employeeId,
                                             @Param("lateDate") LocalDate lateDate);

}
