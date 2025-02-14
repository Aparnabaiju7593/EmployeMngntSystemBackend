package com.OrganizationManagement.organizationManagement.Department;

import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private LeaveRepo leaveRepo;
    public ResponseEntity<?> departmentaDetails(DepartmentModel departmentModel) {
        DepartmentModel departmentModel1=new DepartmentModel();
        departmentModel1.setDepartment(departmentModel.getDepartment());
        departmentRepo.save(departmentModel1);
        return new ResponseEntity<>(departmentModel1, HttpStatus.OK);
    }



//    public ResponseEntity<?> leavestatus(LeaveModel leaveModel) {
//        LeaveModel leaveModel1=new LeaveModel();
//        leaveModel1.setStartDate(leaveModel.getStartDate());
//        leaveModel1.setEndDate(leaveModel.getEndDate());
//        leaveModel1.setReason(leaveModel.getReason());
//        leaveRepo.save(leaveModel1);
//        return new ResponseEntity<>(leaveModel1,HttpStatus.OK);
//    }
}
