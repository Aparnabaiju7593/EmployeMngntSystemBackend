package com.OrganizationManagement.organizationManagement.Employee;

import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/EmployeeDetails")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Registration employee

    @PostMapping(path = "/EmployeeData")
    public ResponseEntity<?>Details(@RequestPart EmployeeModel employeeModel, @RequestPart MultipartFile employeeImage){
        try {
            return employeeService.employeeDetails(employeeModel,employeeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
//add leave request
    @PostMapping(path = "/addleave")
    public ResponseEntity<?>status(@RequestBody LeaveModel leaveModel){
        try {
            return employeeService.leavestatus(leaveModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //late request
    @PostMapping(path = "lateReq")
    public ResponseEntity<?>late(@RequestBody LateModel lateModel){
        try {
            return employeeService.latestatus(lateModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
