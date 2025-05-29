package com.OrganizationManagement.organizationManagement.Admin;

import com.OrganizationManagement.organizationManagement.DepResource.DepResModel;
import com.OrganizationManagement.organizationManagement.DepResource.DepResRepo;
import com.OrganizationManagement.organizationManagement.Department.DepartmentModel;
import com.OrganizationManagement.organizationManagement.Department.DepartmentRepo;
import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationRepo;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeRepo;
import com.OrganizationManagement.organizationManagement.EmployeeDto.*;
import com.OrganizationManagement.organizationManagement.Hr.HrModel;
import com.OrganizationManagement.organizationManagement.Hr.HrRepo;
import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Late.LateRepo;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceModel;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceRepo;
import com.OrganizationManagement.organizationManagement.Resource.ResouceRepo;
import com.OrganizationManagement.organizationManagement.Resource.ResourceModel;
import com.OrganizationManagement.organizationManagement.Role.RoleModel;
import com.OrganizationManagement.organizationManagement.Role.RoleRepo;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import com.OrganizationManagement.organizationManagement.Status.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private LateRepo lateRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private DepResRepo depResRepo;
    @Autowired
    private ResouceRepo resouceRepo;
    @Autowired
    private HrRepo hrRepo;
    @Autowired
    private ReqResourceRepo reqResourceRepo;

    //   admin registration

    public ResponseEntity<?> adminDetails(AdminModel adminModel) {
        AdminModel adminModel1 = new AdminModel();
        adminModel1.setName(adminModel.getName());
        adminModel1.setEmail(adminModel.getEmail());

        // adminModel1.setRoleId(adminModel.getRoleId());
        adminModel1.setPassword(adminModel.getPassword());
        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }
    //login admin and employee and hr

    public ResponseEntity<?> login(RequestDto requestDto) {
        Optional<AdminModel> optionalAdminModel = adminRepo.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword());
        Optional<EmployeeModel> optionalEmployeeModel = employeeRepo.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword());
        Optional<HrModel> optionalHrModel = hrRepo.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword());
        if (optionalAdminModel.isPresent()) {
            AdminModel adminModel = optionalAdminModel.get();

            LoginDto loginDto = new LoginDto();
            loginDto.setId(adminModel.getAdminId());
            Optional<RoleModel> roleModelOptional = roleRepo.findById(adminModel.getRoleId());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRole(roleModel.getRole());
            }
            loginDto.setName(adminModel.getName());
            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        } else if (optionalEmployeeModel.isPresent()) {
            EmployeeModel employeeModel = optionalEmployeeModel.get();

            LoginDto loginDto = new LoginDto();
            loginDto.setId(employeeModel.getEmployeeId());
            loginDto.setDepartmentId(employeeModel.getDepartmentId());

            Optional<RoleModel> roleModelOptional = roleRepo.findById(employeeModel.getRoleId());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRole(roleModel.getRole());
            }
            loginDto.setName(employeeModel.getName());
            return new ResponseEntity<>(loginDto, HttpStatus.OK);

        } else if (optionalHrModel.isPresent()) {
            HrModel hrModel = optionalHrModel.get();
            LoginDto loginDto = new LoginDto();
            loginDto.setId(hrModel.getHrId());
            loginDto.setDepartmentId(hrModel.getDepartmentId());
            Optional<RoleModel> roleModelOptional = roleRepo.findById(hrModel.getRoleId());
            if (roleModelOptional.isPresent()) {
                RoleModel roleModel = roleModelOptional.get();
                loginDto.setRole(roleModel.getRole());
            }
            loginDto.setName(hrModel.getName());
            return new ResponseEntity<>(loginDto, HttpStatus.OK);

        }
            return new ResponseEntity<>("Incorrect  login details", HttpStatus.BAD_REQUEST);
    }
//designation name

    public ResponseEntity<?> addName(DesignationModel designationModel) {
        DesignationModel designationModel1 = new DesignationModel();
        designationModel1.setDesignationName(designationModel.getDesignationName());
        designationRepo.save(designationModel1);
        return new ResponseEntity<>(designationModel1, HttpStatus.OK);
    }


    //admin add designationId
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
        Optional<DesignationModel> designationModelOptional = designationRepo.findById(designationId);
        if (designationModelOptional.isPresent()) {
            DesignationModel designationModel = designationModelOptional.get();
            designationRepo.delete(designationModel);
            return new ResponseEntity<>("designation deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("designation not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //add role

    public ResponseEntity<?> roleDetails(RoleModel roleModel) {
        RoleModel roleModel1 = new RoleModel();
        roleModel1.setRole(roleModel.getRole());
        roleRepo.save(roleModel1);
        return new ResponseEntity<>(roleModel1, HttpStatus.OK);
    }

    //display role

    public ResponseEntity<List<RoleModel>> displayRole() {
        List<RoleModel> roleModelList = roleRepo.findAll();
        return new ResponseEntity<>(roleModelList, HttpStatus.OK);
    }

    //update password
    //forgot password

    public ResponseEntity<?> update(RequestDto requestDto) {
      //  email = email.toLowerCase(); // Normalize email to lowercase


        Optional<AdminModel> adminModelOptional = adminRepo.findByEmail(requestDto.getEmail()) ;
        Optional<EmployeeModel> employeeModelOptional = employeeRepo.findByEmail(requestDto.getEmail());

        if (adminModelOptional.isPresent()) {
            AdminModel adminModel = adminModelOptional.get();
            adminModel.setPassword(requestDto.getPassword());
            adminRepo.save(adminModel);
            return new ResponseEntity<>("Admin password updated successfully", HttpStatus.OK);
        } else if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();
            employeeModel.setPassword(requestDto.getPassword());
            employeeRepo.save(employeeModel);
            return new ResponseEntity<>("Employee password updated successfully", HttpStatus.OK);
        }

        System.out.println("Email not found in both tables!");
        return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
    }
//status add

    public ResponseEntity<?> statusDetails(StatusModel statusModel) {
        StatusModel statusModel1 = new StatusModel();
        statusModel1.setStatusName(statusModel.getStatusName());
        statusRepo.save(statusModel1);
        return new ResponseEntity<>(statusModel1, HttpStatus.OK);
    }

    //get all designation
    public ResponseEntity<List<DesignationModel>> getEmployees() {
        List<DesignationModel> designationModels = designationRepo.findAll();
        return new ResponseEntity<>(designationModels, HttpStatus.OK);
    }

//get all leave req

    public ResponseEntity<List<LeaveModel>> getLeavereq() {
        List<LeaveModel> leaveModels = leaveRepo.findAll();
        return new ResponseEntity<>(leaveModels, HttpStatus.OK);
    }


//get all status

    public ResponseEntity<List<StatusModel>> getAllStatus() {
        List<StatusModel> statusModels = statusRepo.findAll();
        return new ResponseEntity<>(statusModels, HttpStatus.OK);
    }
//update status

    public ResponseEntity<?> updateName(Long statusId, String statusName) {
        Optional<StatusModel> statusModelOptional = statusRepo.findById(statusId);
        if (statusModelOptional.isPresent()) {
            StatusModel statusModel = statusModelOptional.get();
            statusModel.setStatusName(statusName);
            statusRepo.save(statusModel);
            return new ResponseEntity<>("successfully statusname updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }
//delete status

    public ResponseEntity<?> deletestatus(Long statusId) {
        Optional<StatusModel> optionalStatusModel = statusRepo.findById(statusId);
        if (optionalStatusModel.isPresent()) {
            StatusModel statusModel = optionalStatusModel.get();
            statusRepo.delete(statusModel);
            return new ResponseEntity<>("status details deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("status not found", HttpStatus.NOT_FOUND);
        }
    }


    //get employee

    public ResponseEntity<List<EmpDto>> getEmployeesData(Long designationId) {
        List<EmployeeModel> employeeModelList = employeeRepo.findByDesignationId(designationId);

        if (employeeModelList.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // Return empty 200 OK
        }

        // Fetch designation once instead of querying inside the loop
        Optional<DesignationModel> designationModelOptional = designationRepo.findById(designationId);
        String designationName = designationModelOptional.map(DesignationModel::getDesignationName).orElse(null);

        List<EmpDto> empDtoList = new ArrayList<>();

        for (EmployeeModel employeeModel : employeeModelList) { // Fix: Correct iteration
            EmpDto empDto = new EmpDto();
            empDto.setEmployeeId(employeeModel.getEmployeeId());
            empDto.setDepartmentName(employeeModel.getDepartmentName());
            empDto.setName(employeeModel.getName());
            empDto.setEmail(employeeModel.getEmail());
            empDto.setPhnno(employeeModel.getPhnno());
            empDto.setJoinDate(employeeModel.getJoinDate());
            empDto.setDesignationName(designationName); // Set the fetched designation name

            empDtoList.add(empDto);
        }

        return ResponseEntity.ok(empDtoList);
    }




    //get late request

    public ResponseEntity<?> getLateRequest(Long departmentId) {
        List<LateModel>lateModelList=lateRepo.findByDepartmentId(departmentId);
        if (lateModelList.isEmpty()){
            return new ResponseEntity<>("not found late details",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lateModelList,HttpStatus.OK);

    }
//get status

    public ResponseEntity<?> getStatus() {
        List<StatusModel>statusModelList=statusRepo.findAll();
        if (statusModelList.isEmpty()){
            return new ResponseEntity<>("not found status ",HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(statusModelList,HttpStatus.OK);
    }

    //get department

    public ResponseEntity<?> getDepartment(Long departmentId) {
        List<DepartmentModel>departmentModelList=departmentRepo.findByDepartmentId(departmentId);
        if (departmentModelList.isEmpty()){
            return new ResponseEntity<>("department not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentModelList,HttpStatus.OK);
    }

//get all late request

    public ResponseEntity<List<DepartmentModel>> getAllDepartment() {
        List<DepartmentModel>departmentModels=departmentRepo.findAll();
        return new ResponseEntity<>(departmentModels,HttpStatus.OK);
    }

//get All role
    public ResponseEntity<List<RoleModel>> getRole() {
        List<RoleModel>roleModelList=roleRepo.findAll();
        return new ResponseEntity<>(roleModelList,HttpStatus.OK);
    }
//get all resources
    public ResponseEntity<List<ResourceModel>> getAllResources() {
        List<ResourceModel>resourceModelList=resouceRepo.findAll();
        return new ResponseEntity<>(resourceModelList,HttpStatus.OK);
    }
//get all employee
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        List<EmployeeModel>employeeModelList=employeeRepo.findByRoleId(2);
        return new ResponseEntity<>(employeeModelList,HttpStatus.OK);
    }

    public ResponseEntity<List<ResourceDto>> admingetAllResource() {
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        List<ReqResourceModel> reqResourceModelList = reqResourceRepo.findAll();

        if (!reqResourceModelList.isEmpty()) {
            for (ReqResourceModel reqResourceModel : reqResourceModelList) {
                ResourceDto dto = new ResourceDto();
                dto.setReqResourceId(reqResourceModel.getReqResourceId());
                dto.setEmployeeId(reqResourceModel.getEmployeeId());
                dto.setDepartmentId(reqResourceModel.getDepartmentId());
                dto.setQuantity(reqResourceModel.getQuantity());
                dto.setReason(reqResourceModel.getReason());
                dto.setRequestDate(reqResourceModel.getRequestDate());

                dto.setApprovalDate(reqResourceModel.getApprovalDate());

                // Fetch Employee Name
                employeeRepo.findById(reqResourceModel.getEmployeeId()).ifPresent(employeeModel ->
                        dto.setEmployee(employeeModel.getName())
                );

                // Fetch Status Name
                statusRepo.findById(reqResourceModel.getStatusId()).ifPresent(statusModel ->
                        dto.setStatus(statusModel.getStatusName())
                );
//                //fetch
//                departmentRepo.findById(reqResourceModel.getDepartmentId()).ifPresent(departmentModel ->
//                        dto.setDepartment(departmentModel.getDepartment())
//                );
                if (reqResourceModel.getDepartmentId() != null) {
                    departmentRepo.findById(reqResourceModel.getDepartmentId())
                            .ifPresent(departmentModel -> dto.setDepartment(departmentModel.getDepartment()));
                }





                // Fetch Resource Name (Corrected)
                resouceRepo.findById(reqResourceModel.getResourceId()).ifPresent(resourceModel ->
                        dto.setResource(resourceModel.getResource())
                );

                resourceDtoList.add(dto);
            }
        }

        return new ResponseEntity<>(resourceDtoList, HttpStatus.OK);
    }
}













