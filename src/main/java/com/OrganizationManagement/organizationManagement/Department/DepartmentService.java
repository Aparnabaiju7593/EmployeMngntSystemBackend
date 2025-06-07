package com.OrganizationManagement.organizationManagement.Department;

import com.OrganizationManagement.organizationManagement.DepResource.DepResModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationModel;
import com.OrganizationManagement.organizationManagement.Designation.DesignationRepo;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeModel;
import com.OrganizationManagement.organizationManagement.Employee.EmployeeRepo;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LateDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LeaveDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.ResourceDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.TaskDto;
import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Late.LateRepo;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import com.OrganizationManagement.organizationManagement.ReqResDepartment.ReqResDepModel;
import com.OrganizationManagement.organizationManagement.ReqResDepartment.ReqResDepRepo;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceModel;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceRepo;
import com.OrganizationManagement.organizationManagement.Resource.ResouceRepo;
import com.OrganizationManagement.organizationManagement.Resource.ResourceModel;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import com.OrganizationManagement.organizationManagement.Status.StatusRepo;
import com.OrganizationManagement.organizationManagement.Task.TaskModel;
import com.OrganizationManagement.organizationManagement.Task.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private LateRepo lateRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private DesignationRepo designationRepo;
    @Autowired
    private ResouceRepo resouceRepo;
    @Autowired
    private ReqResourceRepo reqResourceRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private ReqResDepRepo reqResDepRepo;


    //add department

    public ResponseEntity<?> departmentaDetails(DepartmentModel departmentModel) {
        DepartmentModel departmentModel1=new DepartmentModel();
        departmentModel1.setDepartment(departmentModel.getDepartment());
        departmentRepo.save(departmentModel1);
        return new ResponseEntity<>(departmentModel1, HttpStatus.OK);
    }

     // add late

    public ResponseEntity<?> lateReqst(Long employeeId) {
        LateModel lateModel1=new LateModel();
        lateModel1.setStatusId(lateModel1.getStatusId());
        lateRepo.save(lateModel1);
        return new ResponseEntity<>(lateModel1,HttpStatus.OK);
    }

    //add task

    public ResponseEntity<?> taskDetails(TaskModel taskModel) {
       TaskModel taskModel1=new TaskModel();
       taskModel1.setTaskName(taskModel.getTaskName());
       taskModel1.setDescription(taskModel.getDescription());
       taskModel1.setDepartmentId(taskModel.getDepartmentId());
       taskModel1.setEmployeeId(taskModel.getEmployeeId());
     //  taskModel1.setStatusId(taskModel.getStatusId());
       taskModel1.setStartDate(LocalDateTime.now());

       taskRepo.save(taskModel1);
       return new ResponseEntity<>(taskModel1,HttpStatus.OK);


    }
    //get all designation

    public ResponseEntity<List<DesignationModel>> getAllEmployees() {
        List<DesignationModel>designationModels=designationRepo.findAll();
        return new ResponseEntity<>(designationModels,HttpStatus.OK);
    }

    //get all leave request

    public ResponseEntity<List<LeaveModel>> getLeavereq() {
        List<LeaveModel>leaveModels=leaveRepo.findAll();
        return new ResponseEntity<>(leaveModels,HttpStatus.OK);
    }

//get all late request

    public ResponseEntity<List<LateModel>> getLateReq() {
        List<LateModel>lateModels=lateRepo.findAll();
        return new ResponseEntity<>(lateModels,HttpStatus.OK);
    }

            //add resources

    public ResponseEntity<?> resourceDetails(ResourceModel resourceModel) {
        ResourceModel resourceModel1=new ResourceModel();
        resourceModel1.setResource(resourceModel.getResource());
        resourceModel1.setQuantity(resourceModel.getQuantity());
//        resourceModel1.setDepartmentId(resourceModel.getDepartmentId());
        resouceRepo.save(resourceModel1);
        return new ResponseEntity<>(resourceModel1,HttpStatus.OK);


    }


                     //approved resource

    public ResponseEntity<?> approvedDep(Long employeeId, Long statusId) {
        // Validate Employee
        EmployeeModel employee = employeeRepo.findById(employeeId)
                .orElse(null);
        if (employee == null) {

            return new ResponseEntity<>("Employee ID not present", HttpStatus.NOT_FOUND);

        }

        // Validate Status
        StatusModel status = statusRepo.findById(statusId)
                .orElse(null);
        if (status == null) {

            return new ResponseEntity<>("status id not found", HttpStatus.NOT_FOUND);

        }

        // Retrieve all resource requests for the employee
        List<ReqResourceModel> reqResources = reqResourceRepo.findByEmployeeId(employeeId);
        if (reqResources.isEmpty()) {

            return new ResponseEntity<>("resource request not found for employee id", HttpStatus.NOT_FOUND);

        }

        // Process each request (OR use latest request)
        for (ReqResourceModel reqResource : reqResources) {
            // Validate Resource
            ResourceModel resource = resouceRepo.findById(reqResource.getResourceId())
                    .orElse(null);
            if (resource == null) {

                return new ResponseEntity<>("resouce not found for given resource id", HttpStatus.NOT_FOUND);

            }

            // Check if resources are available before approval
            if (resource.getQuantity() < reqResource.getQuantity()) {

                return new ResponseEntity<>("not enough resource available", HttpStatus.NOT_FOUND);

            }

            // Deduct the requested quantity from the resource
            resource.setQuantity(resource.getQuantity() - reqResource.getQuantity());
            resouceRepo.save(resource);

            // Update request status
            reqResource.setStatusId(statusId);
            reqResource.setApprovalDate(LocalDateTime.now());
            reqResourceRepo.save(reqResource);
        }

        return new ResponseEntity("Resource request(s) approved successfully.",HttpStatus.OK);
    }


                 //getall department

    public ResponseEntity<List<DepartmentModel>> getAllDep() {
        List<DepartmentModel>departmentModels=departmentRepo.findAll();
        return new ResponseEntity<>(departmentModels,HttpStatus.OK);
    }
                //update department

    public ResponseEntity<?> updateDepmt(Long departmentId, String department) {
        Optional<DepartmentModel>departmentModelOptional=departmentRepo.findById(departmentId);
        if (departmentModelOptional.isPresent()){
            DepartmentModel departmentModel=departmentModelOptional.get();
            departmentModel.setDepartment(department);
            return new ResponseEntity<>("successfully updated department name",HttpStatus.OK);
        }
        return new ResponseEntity<>("department not updated",HttpStatus.NOT_FOUND);
    }
                //delete department

    public ResponseEntity<?> deleteDep(Long departmentId) {
        Optional<DepartmentModel>optionalDepartmentModel=departmentRepo.findById(departmentId);
        if (optionalDepartmentModel.isPresent()){
            DepartmentModel departmentModel= optionalDepartmentModel.get();
            departmentRepo.delete(departmentModel);
            return new ResponseEntity<>("department details deleted ",HttpStatus.OK);
        }
        return new ResponseEntity<>("department not found",HttpStatus.NOT_FOUND);
    }
//get employee

    public ResponseEntity<?> getEmployeeDetail(Long designationId) {
        List<EmployeeModel>employeeModelList=employeeRepo.findByDesignationId(designationId);
        if (employeeModelList.isEmpty()){
            return new ResponseEntity<>("not found employee details",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeModelList,HttpStatus.OK);
    }

//get late request

    public ResponseEntity<?> getLateRequest(Long departmentId) {
        List<LateModel>lateModelList=lateRepo.findByDepartmentId(departmentId);
        if (lateModelList.isEmpty()){
            return new ResponseEntity<>("not found late details",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lateModelList,HttpStatus.OK);
    }



//getall resources

    public ResponseEntity<List<ResourceModel>> getAllResources() {
        List<ResourceModel>resourceModelList=resouceRepo.findAll();
        return new ResponseEntity<>(resourceModelList,HttpStatus.OK);
    }


    //get all late request dto

    public ResponseEntity<List<LateDto>> getAllLate() {
        List<LateDto> lateDtos= new ArrayList<>();
        List<LateModel>lateModelList= lateRepo.findAll();
        if (!lateModelList.isEmpty()){
            for (LateModel lateModel:lateModelList){
                LateDto lateDto=new LateDto();
                lateDto.setLateId(lateModel.getLateId());
//                lateDto.setDepartmentId(lateModel.getDepartmentId());
//                lateDto.setEmployeeId(lateModel.getLateId());
                lateDto.setLateDate(lateModel.getLateDate());
                lateDto.setReason(lateModel.getReason());
                lateDto.setSubmittedTime(lateModel.getSubmittedTime());
                Optional<EmployeeModel>employeeModelOptional=employeeRepo.findById(lateModel.getEmployeeId());
                if (employeeModelOptional.isPresent()){

                    EmployeeModel employeeModel=employeeModelOptional.get();
                    lateDto.setEmployeeName(employeeModel.getName());
                }
                Optional<DepartmentModel>departmentModelOptional=departmentRepo.findById(lateModel.getDepartmentId());
                if (departmentModelOptional.isPresent()){
                    DepartmentModel departmentModel=departmentModelOptional.get();
                    lateDto.setDepartment(departmentModel.getDepartment());
                }
                Optional<StatusModel>statusModelOptional=statusRepo.findById(lateModel.getStatusId());
                if (statusModelOptional.isPresent()){
                    StatusModel statusModel= statusModelOptional.get();
                    lateDto.setStatus(statusModel.getStatusName());
                }
                lateDtos.add(lateDto);

            }
            return new ResponseEntity<>(lateDtos,HttpStatus.OK);

        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }


//get all leave request dto

//    public ResponseEntity<List<LeaveDto>> getAllLeave() {
//        List<LeaveDto>leaveDtos=new ArrayList<>();
//        List<LeaveModel>leaveModelList=leaveRepo.findAll();
//        if (!leaveModelList.isEmpty()){
//            for (LeaveModel leaveModel:leaveModelList){
//                LeaveDto leaveDto= new LeaveDto();
//                leaveDto.setLeaveId(leaveModel.getLeaveId());
//                leaveDto.setEmployeeId(leaveModel.getEmployeeId());
//                leaveDto.setReason(leaveModel.getReason());
//                leaveDto.setStartDate(leaveModel.getStartDate());
//                leaveDto.setEndDate(leaveModel.getEndDate());
//                Optional<EmployeeModel>employeeModelOptional=employeeRepo.findById(leaveModel.getEmployeeId());
//                if (employeeModelOptional.isPresent()){
//                    EmployeeModel employeeModel=employeeModelOptional.get();
//                    leaveDto.setEmployeeName(employeeModel.getName());
//                }
//                Optional<StatusModel>statusModelOptional=statusRepo.findById(leaveModel.getStatusId());
//                if (statusModelOptional.isPresent()){
//                    StatusModel statusModel=statusModelOptional.get();
//                    leaveDto.setStatus(statusModel.getStatusName());
//                }
//                leaveDtos.add(leaveDto);
//            }
//            return new ResponseEntity<>(leaveDtos,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
//    }

    public ResponseEntity<List<LeaveDto>> getAllLeave() {
        List<LeaveDto> leaveDtos = new ArrayList<>();
        List<LeaveModel> leaveModelList = leaveRepo.findAll();

        for (LeaveModel leaveModel : leaveModelList) {
            LeaveDto leaveDto = new LeaveDto();
            leaveDto.setLeaveId(leaveModel.getLeaveId());
            leaveDto.setEmployeeId(leaveModel.getEmployeeId());
            leaveDto.setReason(leaveModel.getReason());
            leaveDto.setStartDate(leaveModel.getStartDate());
            leaveDto.setEndDate(leaveModel.getEndDate());

            // Safe employee lookup
            if (leaveModel.getEmployeeId() != null) {
                employeeRepo.findById(leaveModel.getEmployeeId())
                        .ifPresent(employee -> leaveDto.setEmployeeName(employee.getName()));
            }

            // Safe status lookup
            if (leaveModel.getStatusId() != null) {
                statusRepo.findById(leaveModel.getStatusId())
                        .ifPresent(status -> leaveDto.setStatus(status.getStatusName()));
            }

            leaveDtos.add(leaveDto);
        }

        return ResponseEntity.ok(leaveDtos);
    }


    //get all task by dto


    public ResponseEntity<List<TaskDto>> getAllTask() {
        List<TaskDto> taskDtos = new ArrayList<>();
        List<TaskModel> taskModelList = taskRepo.findAll();

        for (TaskModel taskModel : taskModelList) {
            TaskDto taskDto = new TaskDto();

            // Set basic task info
            taskDto.setTaskId(taskModel.getTaskId());
            taskDto.setDepartmentId(taskModel.getDepartmentId());
            taskDto.setEmployeeId(taskModel.getEmployeeId());
            taskDto.setTaskName(taskModel.getTaskName());
            taskDto.setDescription(taskModel.getDescription());
            taskDto.setStartDate(taskModel.getStartDate());
            taskDto.setProgressTym(taskModel.getProgressTym());
            taskDto.setCompleteTym(taskModel.getCompleteTym());

            // Set employee name
            taskModel.getEmployeeId(); // Ensure it's not null
            if (taskModel.getEmployeeId() != null) {
                employeeRepo.findById(taskModel.getEmployeeId())
                        .ifPresent(emp -> taskDto.setEmployee(emp.getName()));
            }

            // Set department name
            if (taskModel.getDepartmentId() != null) {
                departmentRepo.findById(taskModel.getDepartmentId())
                        .ifPresent(dept -> taskDto.setDepartment(dept.getDepartment()));
            }

            // Set status name
            if (taskModel.getStatusId() != null) {
                statusRepo.findById(taskModel.getStatusId())
                        .ifPresent(status -> taskDto.setStatus(status.getStatusName()));
            }

            taskDtos.add(taskDto);
        }

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }



    public ResponseEntity<List<TaskDto>> getTaskDtobyDep(Long departmentId) {
        List<TaskDto> taskDtos = new ArrayList<>();
        List<TaskModel> taskModelList = taskRepo.findByDepartmentId(departmentId);

        for (TaskModel taskModel : taskModelList) {
            TaskDto taskDto = new TaskDto();

            // Set basic task info
            taskDto.setTaskId(taskModel.getTaskId());
            taskDto.setDepartmentId(taskModel.getDepartmentId());
            taskDto.setEmployeeId(taskModel.getEmployeeId());
            taskDto.setTaskName(taskModel.getTaskName());
            taskDto.setDescription(taskModel.getDescription());
            taskDto.setStartDate(taskModel.getStartDate());
            taskDto.setProgressTym(taskModel.getProgressTym());
            taskDto.setCompleteTym(taskModel.getCompleteTym());

            // Set employee name
            taskModel.getEmployeeId(); // Ensure it's not null
            if (taskModel.getEmployeeId() != null) {
                employeeRepo.findById(taskModel.getEmployeeId())
                        .ifPresent(emp -> taskDto.setEmployee(emp.getName()));
            }

            // Set department name
            if (taskModel.getDepartmentId() != null) {
                departmentRepo.findById(taskModel.getDepartmentId())
                        .ifPresent(dept -> taskDto.setDepartment(dept.getDepartment()));
            }

            // Set status name
            if (taskModel.getStatusId() != null) {
                statusRepo.findById(taskModel.getStatusId())
                        .ifPresent(status -> taskDto.setStatus(status.getStatusName()));
            }

            taskDtos.add(taskDto);
        }

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);

    }

    //department update statusid

    public ResponseEntity<?> updateStatus(Long taskId, Long statusId) {
        Optional<TaskModel>taskModelOptional=taskRepo.findById(taskId);
        if (taskModelOptional.isPresent()){
            TaskModel taskModel=taskModelOptional.get();
            taskModel.setStatusId(statusId);

            taskRepo.save(taskModel);
            return new ResponseEntity<>("successfully updated status id",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //update leave status id

    public ResponseEntity<?> upleaveStatus(Long employeeId, Long statusId) {
        Optional<LeaveModel>leaveModelOptional=leaveRepo.findById(employeeId);
        if (leaveModelOptional.isPresent()){
            LeaveModel leaveModel=leaveModelOptional.get();
            leaveModel.setStatusId(statusId);
            leaveRepo.save(leaveModel);
            return new ResponseEntity<>("successfully update status id",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
//update late status
    public ResponseEntity<?> uplateSatus(Long lateId, Long statusId) {
        Optional<LateModel>lateModelOptional=lateRepo.findById(lateId);
        if (lateModelOptional.isPresent()){
            LateModel lateModel=lateModelOptional.get();
            lateModel.setStatusId(statusId);
            lateRepo.save(lateModel);
            return new ResponseEntity<>("successfully update status id",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not found",HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    //get all resources

    public ResponseEntity<List<ResourceDto>> getAllResource(Long departmentId) {
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        List<ReqResourceModel> reqResourceModelList = reqResourceRepo.findByDepartmentId(departmentId);

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



                // Fetch Resource Name (Corrected)
                resouceRepo.findById(reqResourceModel.getResourceId()).ifPresent(resourceModel ->
                        dto.setResource(resourceModel.getResource())
                );
                resouceRepo.findById(reqResourceModel.getResourceId()).ifPresent(resourceModel ->
                        dto.setAvailablequantity(resourceModel.getQuantity())
                );


                resourceDtoList.add(dto);
            }
        }

        return new ResponseEntity<>(resourceDtoList, HttpStatus.OK);
    }
//leave get by dep
    public ResponseEntity<?> getLeaveDep(Long departmentId) {List<LeaveDto> leaveDtoList = new ArrayList<>();

        List<LeaveModel> leaveModelList = leaveRepo.findByDepartmentId(departmentId);
        if (!leaveModelList.isEmpty()) {
            for (LeaveModel leaveModel : leaveModelList) {
                LeaveDto leaveDto = new LeaveDto();

                // Set Leave ID
                leaveDto.setLeaveId(leaveModel.getLeaveId());

                // Set Employee ID
                leaveDto.setEmployeeId(leaveModel.getEmployeeId());

                // Set Reason, Start Date, and End Date
                leaveDto.setReason(leaveModel.getReason());
                leaveDto.setStartDate(leaveModel.getStartDate());
                leaveDto.setEndDate(leaveModel.getEndDate());

                // Fetch Status Name
                statusRepo.findById(leaveModel.getStatusId()).ifPresent(statusModel ->
                        leaveDto.setStatus(statusModel.getStatusName()));

                // Fetch Employee Name
                employeeRepo.findById(leaveModel.getEmployeeId()).ifPresent(employeeModel ->
                        leaveDto.setEmployeeName(employeeModel.getName()));

                leaveDtoList.add(leaveDto);
            }
        }

        return new ResponseEntity<>(leaveDtoList, HttpStatus.OK);
    }
//getall leave by dep id
    public ResponseEntity<List<LeaveDto>> getAllLeaveDatabydep(Long departmentId) {
        List<LeaveDto> leaveDtos = new ArrayList<>();
        List<LeaveModel> leaveModelList = leaveRepo.findByDepartmentId(departmentId);

        for (LeaveModel leaveModel : leaveModelList) {
            LeaveDto leaveDto = new LeaveDto();
            leaveDto.setLeaveId(leaveModel.getLeaveId());
            leaveDto.setEmployeeId(leaveModel.getEmployeeId());
            leaveDto.setReason(leaveModel.getReason());
            leaveDto.setStartDate(leaveModel.getStartDate());
            leaveDto.setEndDate(leaveModel.getEndDate());

            // Safe employee lookup
            if (leaveModel.getEmployeeId() != null) {
                employeeRepo.findById(leaveModel.getEmployeeId())
                        .ifPresent(employee -> leaveDto.setEmployeeName(employee.getName()));
            }

            // Safe status lookup
            if (leaveModel.getStatusId() != null) {
                statusRepo.findById(leaveModel.getStatusId())
                        .ifPresent(status -> leaveDto.setStatus(status.getStatusName()));
            }

            leaveDtos.add(leaveDto);
        }

        return ResponseEntity.ok(leaveDtos);
    }

    //get late hr view
    public ResponseEntity<List<LateDto>> getAllLateDatabydep(Long departmentId) {
        List<LateDto> lateDtos= new ArrayList<>();
        List<LateModel>lateModelList= lateRepo.findByDepartmentId(departmentId);
        if (!lateModelList.isEmpty()){
            for (LateModel lateModel:lateModelList){
                LateDto lateDto=new LateDto();
                lateDto.setLateId(lateModel.getLateId());
//                lateDto.setDepartmentId(lateModel.getDepartmentId());
//                lateDto.setEmployeeId(lateModel.getLateId());
                lateDto.setLateDate(lateModel.getLateDate());
                lateDto.setReason(lateModel.getReason());
                lateDto.setSubmittedTime(lateModel.getSubmittedTime());
                Optional<EmployeeModel>employeeModelOptional=employeeRepo.findById(lateModel.getEmployeeId());
                if (employeeModelOptional.isPresent()){

                    EmployeeModel employeeModel=employeeModelOptional.get();
                    lateDto.setEmployeeName(employeeModel.getName());
                }
                Optional<DepartmentModel>departmentModelOptional=departmentRepo.findById(lateModel.getDepartmentId());
                if (departmentModelOptional.isPresent()){
                    DepartmentModel departmentModel=departmentModelOptional.get();
                    lateDto.setDepartment(departmentModel.getDepartment());
                }
                Optional<StatusModel>statusModelOptional=statusRepo.findById(lateModel.getStatusId());
                if (statusModelOptional.isPresent()){
                    StatusModel statusModel= statusModelOptional.get();
                    lateDto.setStatus(statusModel.getStatusName());
                }
                lateDtos.add(lateDto);

            }
            return new ResponseEntity<>(lateDtos,HttpStatus.OK);

        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }

//resource approval

    public ResponseEntity<?> addApprovals(Long reqResourceId, Long employeeId, Long statusId,String remarks) {
        Optional<ReqResourceModel> reqResourceModelOptional=reqResourceRepo.findByReqResourceIdAndEmployeeId(reqResourceId,employeeId);
        if (reqResourceModelOptional.isPresent()){
            ReqResourceModel reqResourceModel=reqResourceModelOptional.get();
            reqResourceModel.setStatusId(statusId);
            reqResourceModel.setApprovalDate(LocalDateTime.now());
            reqResourceModel.setRemarks(remarks);
            reqResourceRepo.save(reqResourceModel);
            return new ResponseEntity<>(reqResourceModel,HttpStatus.OK);
        }
        return new ResponseEntity<>("Request resourceid not found",HttpStatus.NOT_FOUND);
    }

//leave approval by hr

    public ResponseEntity<?> addApprovalsleave(Long leaveId, Long statusId) {
        Optional<LeaveModel> leaveModelOptional=leaveRepo.findById(leaveId);
        if (leaveModelOptional.isPresent()){
            LeaveModel leaveModel=leaveModelOptional.get();
            leaveModel.setStatusId(statusId);

            leaveRepo.save(leaveModel);
            return new ResponseEntity<>(leaveModel,HttpStatus.OK);
        }
        return new ResponseEntity<>("leave id not found",HttpStatus.NOT_FOUND);
    }
    //late approval by hr

    public ResponseEntity<?> addApprovalslate(Long lateId, Long statusId) {
        Optional<LateModel> lateModelOptional=lateRepo.findById(lateId);
        if (lateModelOptional.isPresent()){
            LateModel lateModel=lateModelOptional.get();
            lateModel.setStatusId(statusId);

            lateRepo.save(lateModel);
            return new ResponseEntity<>(lateModel,HttpStatus.OK);
        }
        return new ResponseEntity<>("late id not found",HttpStatus.NOT_FOUND);
    }
//hr view employee list
    public ResponseEntity<List<EmployeeModel>> listEmployeesbyhr(Long departmentId) {
        List<EmployeeModel>employeeModelList=employeeRepo.findByDepartmentIdAndRoleId(departmentId,2);
        return new ResponseEntity<>(employeeModelList,HttpStatus.OK);
    }



    //department request resources

//    public ResponseEntity<?> resourceDep(ReqResDepModel reqResDepModel) {
//        ReqResDepModel reqResDepModel1=new ReqResDepModel();
//        reqResDepModel1.setDepartmentId(reqResDepModel.getDepartmentId());
//        reqResDepModel1.setReason(reqResDepModel.getReason());
//        reqResDepModel1.setRequestedDate(reqResDepModel.getRequestedDate());
//        reqResDepModel1.setApprovedDate(reqResDepModel.getApprovedDate());
//        reqResDepRepo.save(reqResDepModel1);
//        return new ResponseEntity<>(reqResDepModel1,HttpStatus.OK);
//    }




}
