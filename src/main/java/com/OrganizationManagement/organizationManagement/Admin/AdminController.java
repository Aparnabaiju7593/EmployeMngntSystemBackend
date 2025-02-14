package com.OrganizationManagement.organizationManagement.Admin;

import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Role.RoleModel;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "api/AdminDetails/")
public class AdminController {
    @Autowired
    private AdminService adminService;
//    @Autowired
//    private DesignationService designationService;

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


    //  Add designation by admin


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
    public ResponseEntity<?>update(@RequestParam String email,@RequestParam String password ){
        try {
            return adminService.update(email,password);
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

}
