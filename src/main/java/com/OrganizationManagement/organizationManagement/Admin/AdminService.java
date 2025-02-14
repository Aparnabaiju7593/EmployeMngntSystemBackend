package com.OrganizationManagement.organizationManagement.Admin;

import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationRepo;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeRepo;
import com.OrganizationManagement.organizationManagement.Role.RoleModel;
import com.OrganizationManagement.organizationManagement.Role.RoleRepo;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import com.OrganizationManagement.organizationManagement.Status.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private DesignationRepo designationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private StatusRepo statusRepo;

    //   admin registration

    public ResponseEntity<?> adminDetails(AdminModel adminModel) {
        AdminModel adminModel1=new AdminModel();
        adminModel1.setName(adminModel.getName());
        adminModel1.setEmail(adminModel.getEmail());
       // adminModel1.setRoleId(adminModel.getRoleId());
        adminModel1.setPassword(adminModel.getPassword());
        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }
//login admin and employee
//    public ResponseEntity<?> login(String email, String password) {
//        Optional<AdminModel> optionalAdminModel = adminRepo.findByEmailAndPassword(email, password);
//       Optional<EmployeeModel> optionalEmployeeModel = employeeRepo.findByEmailAndPassword(email, password);
//        if (optionalAdminModel.isPresent()) {
//            return new ResponseEntity<>(" admin login success", HttpStatus.OK);
//        } else if (optionalEmployeeModel.isPresent()) {
//            return new ResponseEntity<>("employee login success",HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("email and password incorrect", HttpStatus.OK);
//        }
//    }
public ResponseEntity<?> login(RequestDto requestDto) {
   Optional<AdminModel>optionalAdminModel=adminRepo.findByEmailAndPassword(requestDto.getEmail(),requestDto.getPassword());
   Optional<EmployeeModel>optionalEmployeeModel=employeeRepo.findByEmailAndPassword(requestDto.getEmail(),requestDto.getPassword());
   if (optionalAdminModel.isPresent()){
       AdminModel adminModel= optionalAdminModel.get();

       LoginDto loginDto=new LoginDto();
       loginDto.setId(adminModel.getAdminId());
       Optional<RoleModel>roleModelOptional=roleRepo.findById(adminModel.getRoleId());
       if (roleModelOptional.isPresent()){
           RoleModel roleModel=roleModelOptional.get();
           loginDto.setRole(roleModel.getRole());
       }
       loginDto.setName(adminModel.getName());
       return new ResponseEntity<>(loginDto,HttpStatus.OK);
   }
   else if (optionalEmployeeModel.isPresent()){
       EmployeeModel employeeModel= optionalEmployeeModel.get();

       LoginDto loginDto=new LoginDto();
       loginDto.setId(employeeModel.getEmployeeId());
       Optional<RoleModel>roleModelOptional=roleRepo.findById(employeeModel.getRoleId());
       if (roleModelOptional.isPresent()){
           RoleModel roleModel=roleModelOptional.get();
           loginDto.setRole(roleModel.getRole());
       }
       loginDto.setName(employeeModel.getName());
       return new ResponseEntity<>(loginDto,HttpStatus.OK);

   }
   else {
       return new ResponseEntity<>("Incorrect  login details",HttpStatus.BAD_REQUEST);
   }
}
//designation name

    public ResponseEntity<?> addName(DesignationModel designationModel) {
        DesignationModel designationModel1=new DesignationModel();
        designationModel1.setDesignationName(designationModel.getDesignationName());
        designationRepo.save(designationModel1);
        return new ResponseEntity<>(designationModel1,HttpStatus.OK);
    }



//add designation by admin
    public ResponseEntity<?> designationAdd(Long employeeId, Long designationId) {
        Optional<EmployeeModel> optionalEmployeeModel = employeeRepo.findById(employeeId);

        if (optionalEmployeeModel.isPresent()) {
            EmployeeModel existingEmployee = optionalEmployeeModel.get();

            existingEmployee.setDesignationId(designationId); // Assuming a designation field


            employeeRepo.save(existingEmployee); // Save updated entity

            return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
        }

        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }

             //delete designation

    public ResponseEntity<?> deleteDesination(Long designationId) {
        Optional<DesignationModel>designationModelOptional=designationRepo.findById(designationId);
        if (designationModelOptional.isPresent()){
            DesignationModel designationModel=designationModelOptional.get();
            designationRepo.delete(designationModel);
            return new ResponseEntity<>("designation deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("designation not found",HttpStatus.INTERNAL_SERVER_ERROR);
    }

                    //add role

    public ResponseEntity<?> roleDetails(RoleModel roleModel) {
        RoleModel roleModel1=new RoleModel();
        roleModel1.setRole(roleModel.getRole());
        roleRepo.save(roleModel1);
        return new ResponseEntity<>(roleModel1,HttpStatus.OK);
    }

                    //display role

    public ResponseEntity<List<RoleModel>> displayRole() {
        List<RoleModel>roleModelList=roleRepo.findAll();
        return new ResponseEntity<>(roleModelList,HttpStatus.OK);
    }

    //update password
    //forgot password

    public ResponseEntity<?> update(String email, String password) {
        email = email.toLowerCase(); // Normalize email to lowercase


        Optional<AdminModel> adminModelOptional = adminRepo.findByEmail(email);
        Optional<EmployeeModel> employeeModelOptional = employeeRepo.findByEmail(email);

        if (adminModelOptional.isPresent()) {
            AdminModel adminModel = adminModelOptional.get();
            adminModel.setPassword(password);
            adminRepo.save(adminModel);
            return new ResponseEntity<>("Admin password updated successfully", HttpStatus.OK);
        }
        else if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();
            employeeModel.setPassword(password);
            employeeRepo.save(employeeModel);
            return new ResponseEntity<>("Employee password updated successfully", HttpStatus.OK);
        }

        System.out.println("Email not found in both tables!");
        return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
    }
//status add

    public ResponseEntity<?> statusDetails(StatusModel statusModel) {
        StatusModel statusModel1=new StatusModel();
        statusModel1.setStatusName(statusModel.getStatusName());
        statusRepo.save(statusModel1);
        return new ResponseEntity<>(statusModel1,HttpStatus.OK);
    }
}




