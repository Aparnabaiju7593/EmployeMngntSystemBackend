package com.OrganizationManagement.organizationManagement.Hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/HrDetails")
public class HrController {
    @Autowired
    private HrService hrService;

    //registration

    @PostMapping(path = "/regHr")
    public ResponseEntity<?>registrationHr(@RequestBody HrModel hrModel){
        try {
            return hrService.HrRegistration(hrModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //login

//    @PostMapping(path = "/login")
//    public ResponseEntity<?>loginHr(@RequestParam String email,@RequestParam String password){
//        try {
//            return hrService.loginDetails(email,password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
