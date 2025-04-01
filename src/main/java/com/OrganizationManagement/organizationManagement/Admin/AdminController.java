package com.OrganizationManagement.organizationManagement.Admin;

import com.OrganizationManagement.organizationManagement.DepResource.DepResModel;
import com.OrganizationManagement.organizationManagement.Department.DepartmentModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeService;
import com.OrganizationManagement.organizationManagement.EmployeeDto.EmpDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LateDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.RequestDto;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Role.RoleModel;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/AdminDetails")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;


    // Admin Registration

    @PostMapping(path = "/addData")
    public ResponseEntity<?>Details(@RequestBody AdminModel adminModel){
        try {
            return adminService.adminDetails(adminModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Admin and employee  Login

    @PostMapping(path = "/login")
    public ResponseEntity<?>login(@RequestBody RequestDto requestDto){
        try {
            return adminService.login(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
            //add designation name
    @PostMapping(path = "/addDesignationName")
    public ResponseEntity<?>addName(@RequestBody DesignationModel designationModel){
        try {
            return adminService.addName(designationModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //admin Add designationid


    @PostMapping(path = "/addDesignation")
    public ResponseEntity<?>designationAdding(@RequestParam Long employeeId,@RequestParam Long designationId) {
        try {
            return adminService.designationAdd(employeeId, designationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //delete designation by admin
    @DeleteMapping(path = "/deleteDesignation")
    public ResponseEntity<?>delete(@RequestParam Long designationId){
        try {
            return adminService.deleteDesination(designationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //add role
    @PostMapping(path = "/RoleData")
    public ResponseEntity<?>Details(@RequestBody RoleModel roleModel){
        try {
            return adminService.roleDetails(roleModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //display role
    @GetMapping (path = "/displayRole")
    public ResponseEntity<List<RoleModel>>displayrole(){
        return adminService.displayRole();
    }

//update password
    @PutMapping(path = "/updatepassword")
    public ResponseEntity<?>update(@RequestBody RequestDto requestDto){
        try {
            return adminService.update(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //add status
    @PostMapping(path = "/statusAdd")
    public ResponseEntity<?>statusadd(@RequestBody StatusModel statusModel){
        try {
            return adminService.statusDetails(statusModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //list all designation

    @GetMapping(path = "getAllEmployee")
    public ResponseEntity<List<DesignationModel>>getAllEmp(){
        return adminService.getEmployees();
    }

    //list Leave request

    @GetMapping(path ="getLeave")
    public ResponseEntity<List<LeaveModel>>allLeave(){
        return adminService.getLeavereq();
    }

//add DepResource

//    @PostMapping(path = "addDepResource")
//    public ResponseEntity<?>AddDepResource(@RequestBody DepResModel depResModel){
//        try {
//            return adminService.depresDetails(depResModel);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //get all status
    @GetMapping(path = "/listStatus")
    public ResponseEntity<List<StatusModel>>getAllStatus(){return adminService.getAllStatus();}

    //update status

    @PutMapping(path = "/updateStatus")
    public ResponseEntity<?>updateName(@RequestParam Long statusId,@RequestParam String statusName){
        try {
            return adminService.updateName(statusId,statusName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //delete status name

    @DeleteMapping(path = "/deletestatus")
    public ResponseEntity<?>deletestatus(@RequestParam Long statusId){
        return adminService.deletestatus(statusId);
    }
//admin get employee

    @GetMapping(path = "/getEmployee")
    public ResponseEntity<List<EmpDto>>getEmployeesData(@RequestParam Long designationId){
        return adminService.getEmployeesData(designationId);
    }
    //get late request by department id
    @GetMapping(path = "/getlaterequest")
    public ResponseEntity<?>getLateRequest(@RequestParam Long departmentId){
        return adminService.getLateRequest(departmentId);
    }
    //get status by statusId

    @GetMapping(path = "/getStatus")
    public ResponseEntity<?>getSatatus(@RequestParam Long statusId){
        return adminService.getStatus(statusId);
    }
//get department by departmentId

    @GetMapping("/getDepartment")
    public ResponseEntity<?>getDepartment(@RequestParam Long departmentId){
        return adminService.getDepartment(departmentId);
    }

//list

@GetMapping(path = "/listallDepartment")
    public ResponseEntity<List<DepartmentModel>>getAllDepartment(){
        return adminService.getAllDepartment();
}


    @GetMapping(path = "/getAllRoles")
    public ResponseEntity<List<RoleModel>>getAll(){
        return adminService.getRole();
    }







}
