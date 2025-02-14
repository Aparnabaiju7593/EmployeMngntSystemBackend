package com.OrganizationManagement.organizationManagement.Employee;

import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Late.LateRepo;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private LateRepo lateRepo;

    public ResponseEntity<?> employeeDetails(EmployeeModel employeeModel, MultipartFile employeeImage) throws IOException {
        EmployeeModel employeeModel1=new EmployeeModel();

        employeeModel1.setName(employeeModel.getName());
        employeeModel1.setEmail(employeeModel.getEmail());
        employeeModel1.setPassword(employeeModel.getPassword());
        employeeModel1.setPhnno(employeeModel.getPhnno());
      //  employeeModel1.setRoleId(employeeModel.getRoleId());
        employeeModel1.setJoinDate(employeeModel.getJoinDate());
        //file upload(multipart)
        employeeModel1.setEmployeeImage(employeeImage.getBytes());
        employeeRepo.save(employeeModel1);
        return new ResponseEntity<>(employeeModel1, HttpStatus.OK);
    }
            //leave

    public ResponseEntity<?> leavestatus(LeaveModel leaveModel) {

        LeaveModel leaveModel1=new LeaveModel();
        leaveModel1.setStartDate(leaveModel.getStartDate());
        leaveModel1.setEndDate(leaveModel.getEndDate());
        leaveModel1.setReason(leaveModel.getReason());
        leaveModel1.setStatusId(leaveModel.getStatusId());
        leaveRepo.save(leaveModel1);
        return new ResponseEntity<>(leaveModel1,HttpStatus.OK);
    }

    public ResponseEntity<?> latestatus(LateModel lateModel) {
        LateModel lateModel1=new LateModel();
        lateModel1.setLateDate(lateModel.getLateDate());
        lateModel1.setReason(lateModel.getReason());
        lateRepo.save(lateModel1);
        return new ResponseEntity<>(lateModel1,HttpStatus.OK);

    }
}
