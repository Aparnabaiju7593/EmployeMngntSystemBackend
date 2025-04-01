package com.OrganizationManagement.organizationManagement.Department;

import com.OrganizationManagement.organizationManagement.DepResource.DepResModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeService;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LateDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LeaveDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.TaskDto;
import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import com.OrganizationManagement.organizationManagement.ReqResDepartment.ReqResDepModel;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceModel;
import com.OrganizationManagement.organizationManagement.Resource.ResourceModel;
import com.OrganizationManagement.organizationManagement.Task.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/departmentadetails")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    //add department

    @PostMapping(path = "/addData")
    public ResponseEntity<?>Details(@RequestBody DepartmentModel departmentModel){
        try {
            return departmentService.departmentaDetails(departmentModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
     //department accept reject

    @GetMapping(path = "/lateReject")
    public ResponseEntity<?>lateDetail(@RequestParam Long employeeId) {
        try {
            return departmentService.lateReqst(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

        //task add

    @PostMapping(path = "/addTask")
    public ResponseEntity<?>TaskAdd(@RequestBody TaskModel taskModel){
        try {
            return departmentService.taskDetails(taskModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

         //list all employees

    @GetMapping(path ="getEmployee")
    public ResponseEntity<List<DesignationModel>>allEmployee(){
        return departmentService.getAllEmployees();
    }

    //list all leave request

    @GetMapping(path = "/getLeave")
    public ResponseEntity<List<LeaveModel>>allLeave(){
        return departmentService.getLeavereq();
    }

    //list all late request

    @GetMapping(path = "getLate")
    public ResponseEntity<List<LateModel>>allLate(){
        return departmentService.getLateReq();
    }

//add resources

    @PostMapping(path = "/addResources")
    public ResponseEntity<?>ResourceAdd(@RequestBody ResourceModel resourceModel){
        try {
            return departmentService.resourceDetails(resourceModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //approved or rejected resouces update

    @PutMapping(path = "/addApproval")
    public ResponseEntity<?>approvedDep(@RequestParam Long employeeId, @RequestParam Long statusId){
        try {
            return departmentService.approvedDep(employeeId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
return new ResponseEntity<>( "something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //add dep resource request

//    @PostMapping(path = "DepreqResource")
//    public ResponseEntity<?>Depreq(@RequestBody ReqResDepModel reqResDepModel){
//        try {
//           return departmentService.resourceDep(reqResDepModel);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

//get all department
    @GetMapping(path = "listdepartment")
    public ResponseEntity<List<DepartmentModel>>getAllDep(){
        return departmentService.getAllDep();
    }


//update department

@PutMapping(path = "/updateDep")
    public ResponseEntity<?>updateDepmt(@RequestParam Long departmentId,@RequestParam String department){
        try {
            return departmentService.updateDepmt(departmentId,department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

 //delete department
    @DeleteMapping(path = "/deleteDep")
    public ResponseEntity<?>deleteDep(@RequestParam Long departmentId){
        return departmentService.deleteDep(departmentId);
    }

    //department get employee

    @GetMapping(path = "/getEmployeeDep")
    public ResponseEntity<?>getEmployeeDetail(@RequestParam Long designationId){
        return departmentService.getEmployeeDetail(designationId);
    }
//department get late request by departmentId

    @GetMapping(path = "/getlateRequest")
    public ResponseEntity<?>getlateRequest(@RequestParam Long departmentId){
        return departmentService.getLateRequest(departmentId);
    }


    //list resources

    @GetMapping(path = "/getResources")
    public ResponseEntity<List<ResourceModel>>getAllResources(){
        return departmentService.getAllResources();
    }

    //get all late request dto

    @GetMapping(path = "/getAllLateDto")
    public ResponseEntity<List<LateDto>>getAllLateData(){
        return  departmentService.getAllLate();

    }

    //get all leave reqestdto

    @GetMapping(path = "/getLeaveDto")
    public ResponseEntity<List<LeaveDto>>getAllLeaveData(){
        return departmentService.getAllLeave();
    }

//get all task details dto

    @GetMapping(path = "/getTaskDto")
    public ResponseEntity<List<TaskDto>>getAllTaskData(){
        return departmentService.getAllTask();
    }


    //update statusid task by taskId

    @PutMapping(path = "/taskStatusUpd")
    public ResponseEntity<?>updatestatusId(@RequestParam Long taskId,@RequestParam Long statusId){
        try {
            return departmentService.updateStatus(taskId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //leave status id update

    @PutMapping(path = "/leaveStatusIdUpdate")
    public ResponseEntity<?>UpleaveStatus(@RequestParam Long employeeId,@RequestParam Long statusId){
        try {
            return departmentService.upleaveStatus(employeeId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
//update late statusid
    @PutMapping(path = "/lateStatusIdUpdate")
    public ResponseEntity<?>upLateStatus(@RequestParam Long lateId,@RequestParam Long statusId){
        try {
            return departmentService.uplateSatus(lateId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
