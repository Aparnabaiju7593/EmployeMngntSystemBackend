package com.OrganizationManagement.organizationManagement.Employee;

import com.OrganizationManagement.organizationManagement.EmployeeDto.TaskDto;
import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.ReqResDepartment.ReqResDepModel;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceModel;
import com.OrganizationManagement.organizationManagement.Task.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
@CrossOrigin
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
    @PostMapping(path = "/lateReq")
    public ResponseEntity<?>late(@RequestBody LateModel lateModel){
        try {
            return employeeService.latestatus(lateModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // request resource

    @PostMapping(path = "reqResource")
    public ResponseEntity<?>requestres(@RequestBody ReqResourceModel reqResourceModel){
        try {
            return employeeService.reqResource(reqResourceModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
//update progresstym task

    @PutMapping("/updatepgrs")
    public ResponseEntity<?>updatepgrs(@RequestParam Long taskId){
        try {
            return employeeService.updateprs(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update complete tym

    @PutMapping("/updatecomptym")

    public ResponseEntity<?>updatecomtym(@RequestParam Long taskId){
        try {
            return employeeService.updatecomtym(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


         //employee view task

    @GetMapping("/getEmployeeTask")
    public ResponseEntity<List<TaskDto>> getTaskView(@RequestParam(required = false) Long employeeId) {
        if (employeeId == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        return employeeService.getTaskView(employeeId);
    }
//employee view leave
    @GetMapping("/getLeaveView")
    public ResponseEntity<?>getLeaveView(@RequestParam Long employeeId){
        return employeeService.getLeaveView(employeeId);
    }

    //employee view late

    @GetMapping("/getLateview")
    public ResponseEntity<?>getLateview(@RequestParam Long employeeId){
        return employeeService.getLateView(employeeId);
    }



}
