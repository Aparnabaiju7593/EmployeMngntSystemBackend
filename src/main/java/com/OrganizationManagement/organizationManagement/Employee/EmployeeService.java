package com.OrganizationManagement.organizationManagement.Employee;

import com.OrganizationManagement.organizationManagement.Admin.AdminModel;
import com.OrganizationManagement.organizationManagement.Department.DepartmentRepo;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LateDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.LeaveDto;
import com.OrganizationManagement.organizationManagement.EmployeeDto.TaskDto;
import com.OrganizationManagement.organizationManagement.Late.LateModel;
import com.OrganizationManagement.organizationManagement.Late.LateRepo;
import com.OrganizationManagement.organizationManagement.Leave.LeaveModel;
import com.OrganizationManagement.organizationManagement.Leave.LeaveRepo;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceModel;
import com.OrganizationManagement.organizationManagement.ReqResource.ReqResourceRepo;
import com.OrganizationManagement.organizationManagement.Status.StatusModel;
import com.OrganizationManagement.organizationManagement.Status.StatusRepo;
import com.OrganizationManagement.organizationManagement.Task.TaskModel;
import com.OrganizationManagement.organizationManagement.Task.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private LateRepo lateRepo;
    @Autowired
    private ReqResourceRepo reqResourceRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

//employee registration

    public ResponseEntity<?> employeeDetails(EmployeeModel employeeModel, MultipartFile employeeImage) throws IOException {
        EmployeeModel employeeModel1 = new EmployeeModel();

        employeeModel1.setName(employeeModel.getName());
        employeeModel1.setEmail(employeeModel.getEmail());
        employeeModel1.setPassword(employeeModel.getPassword());
        employeeModel1.setPhnno(employeeModel.getPhnno());
        //  employeeModel1.setDesignationId(employeeModel.getDesignationId());
        employeeModel1.setDepartmentName(employeeModel.getDepartmentName());
        //  employeeModel1.setRoleId(employeeModel.getRoleId());
        employeeModel1.setDepartmentId(employeeModel.getDepartmentId());
        employeeModel1.setJoinDate(employeeModel.getJoinDate());
        employeeModel1.setRoleId(employeeModel.getRoleId());
        //file upload(multipart)
        employeeModel1.setEmployeeImage(employeeImage.getBytes());
        employeeRepo.save(employeeModel1);
        return new ResponseEntity<>(employeeModel1, HttpStatus.OK);
    }
    //leave

    public ResponseEntity<?> leavestatus(LeaveModel leaveModel) {

        LeaveModel leaveModel1 = new LeaveModel();
        leaveModel1.setStartDate(leaveModel.getStartDate());
        leaveModel1.setEndDate(leaveModel.getEndDate());
        leaveModel1.setReason(leaveModel.getReason());
        leaveModel1.setEmployeeId(leaveModel.getEmployeeId());
        leaveModel1.setStatusId(leaveModel.getStatusId());
        // leaveModel1.setTimestamp(leaveModel.getTimestamp());

        leaveRepo.save(leaveModel1);
        return new ResponseEntity<>(leaveModel1, HttpStatus.OK);
    }
//late status


    public ResponseEntity<?> latestatus(LateModel lateModel) {
        LateModel lateModel1 = new LateModel();
        lateModel1.setLateDate(lateModel.getLateDate());
        lateModel1.setDepartmentId(lateModel.getDepartmentId());
        lateModel1.setEmployeeId(lateModel.getEmployeeId());
        lateModel1.setReason(lateModel.getReason());
        lateModel1.setSubmittedTime(LocalDateTime.now());
        lateRepo.save(lateModel1);
        return new ResponseEntity<>(lateModel1, HttpStatus.OK);

    }

//employee request resources

    public ResponseEntity<?> reqResource(ReqResourceModel reqResourceModel) {
        ReqResourceModel reqResourceModel1 = new ReqResourceModel();
        reqResourceModel1.setResourceId(reqResourceModel.getResourceId());
        reqResourceModel1.setEmployeeId(reqResourceModel.getEmployeeId());
        reqResourceModel1.setReason(reqResourceModel.getReason());
        reqResourceModel1.setQuantity(reqResourceModel.getQuantity());
        reqResourceModel1.setRequestDate(reqResourceModel.getRequestDate());
        reqResourceModel1.setApprovalDate(reqResourceModel.getApprovalDate());
        reqResourceRepo.save(reqResourceModel1);
        return new ResponseEntity<>(reqResourceModel1, HttpStatus.OK);
    }
    //add progress tym

    public ResponseEntity<?> pgrstym(TaskModel taskModel) {
        TaskModel taskModel1 = new TaskModel();
        taskModel1.setProgressTym(LocalTime.now());
        taskRepo.save(taskModel1);
        return new ResponseEntity<>(taskModel1, HttpStatus.OK);
    }

    //update progresss  tym
    public ResponseEntity<?> updateprs(Long taskId) {
        Optional<TaskModel> taskModelOptional = taskRepo.findById(taskId);
        if (taskModelOptional.isPresent()) {
            TaskModel taskModel = taskModelOptional.get();
            taskModel.setProgressTym(LocalTime.now());
            taskRepo.save(taskModel);
            return new ResponseEntity<>("update progress tym", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
    }

    //update complete time
    public ResponseEntity<?> updatecomtym(Long taskId) {
        Optional<TaskModel> taskModelOptional = taskRepo.findById(taskId);
        if (taskModelOptional.isPresent()) {
            TaskModel taskModel = taskModelOptional.get();
            taskModel.setCompleteTym(LocalTime.now());
            taskRepo.save(taskModel);
            return new ResponseEntity<>("update complete time", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
    }

    //employee view task

    public ResponseEntity<List<TaskDto>> getTaskView(Long employeeId) {
        List<TaskModel> taskModelList = taskRepo.findByEmployeeId(employeeId);

        if (taskModelList.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<TaskDto> taskDtoList = taskModelList.stream().map(taskModel -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(taskModel.getTaskId());
            taskDto.setTaskName(taskModel.getTaskName());
            taskDto.setDescription(taskModel.getDescription());
            taskDto.setStartDate(taskModel.getStartDate());

            statusRepo.findById(taskModel.getStatusId()).ifPresent(statusModel ->
                    taskDto.setStatus(statusModel.getStatusName()));

            return taskDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(taskDtoList);
    }

    //leave view

    public ResponseEntity<?> getLeaveView(Long employeeId) {
        List<LeaveDto> leaveDtoList = new ArrayList<>();

        List<LeaveModel> leaveModelList = leaveRepo.findByEmployeeId(employeeId);
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


    //late view

    public ResponseEntity<?> getLateView(Long employeeId) {
        List<LateDto> lateDtoList = new ArrayList<>();
        List<LateModel> lateModelList = lateRepo.findByEmployeeId(employeeId);
        if (!lateModelList.isEmpty()) {
            for (LateModel lateModel : lateModelList) {
                LateDto lateDto = new LateDto();
                //set late id and employeeid
                lateDto.setLateId(lateModel.getLateId());
                lateDto.setEmployeeId(lateModel.getEmployeeId());
                lateDto.setDepartmentId(lateModel.getDepartmentId());
                //set reason,late date ,submitted time
                lateDto.setReason(lateModel.getReason());
                lateDto.setLateDate(lateModel.getLateDate());
                lateDto.setSubmittedTime(lateModel.getSubmittedTime());
                //fetch statusname
                statusRepo.findById(lateModel.getStatusId()).ifPresent(statusModel ->
                        lateDto.setStatus(statusModel.getStatusName()));

                // Fetch Employee Name
                employeeRepo.findById(lateModel.getEmployeeId()).ifPresent(employeeModel ->
                        lateDto.setEmployeeName(employeeModel.getName()));

                //fetch depaertment name
                departmentRepo.findById(lateModel.getDepartmentId()).ifPresent(departmentModel ->
                        lateDto.setDepartment(departmentModel.getDepartment()));

                lateDtoList.add(lateDto);

            }

        }
        return new ResponseEntity<>(lateDtoList, HttpStatus.OK);

    }


    public ResponseEntity<?> updatetaskStatus(Long taskId, Long statusId) {
        Optional<TaskModel> taskModelOptional = taskRepo.findById(taskId);

        if (taskModelOptional.isPresent()) {
            TaskModel taskModel = taskModelOptional.get();

            // Optional: Check if the statusId is valid (e.g., in statusRepo)

            taskModel.setStatusId(statusId);
            taskRepo.save(taskModel);

            return new ResponseEntity<>("Task status updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

}



