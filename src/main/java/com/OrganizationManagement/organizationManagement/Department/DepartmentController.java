package com.OrganizationManagement.organizationManagement.Department;

import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/departmentadetails")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @PostMapping(path = "/addData")
    public ResponseEntity<?>Details(@RequestBody DepartmentModel departmentModel){
        try {
            return departmentService.departmentaDetails(departmentModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
