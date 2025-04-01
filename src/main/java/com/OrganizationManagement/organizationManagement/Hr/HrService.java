package com.OrganizationManagement.organizationManagement.Hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HrService {
    @Autowired
    private HrRepo hrRepo;

    public ResponseEntity<?> HrRegistration(HrModel hrModel) {
        HrModel hrModel1=new HrModel();
        hrModel1.setHrName(hrModel.getHrName());
        hrModel1.setEmail(hrModel.getEmail());
        hrModel1.setPassword(hrModel.getPassword());
        hrModel1.setDepartmentId(hrModel.getDepartmentId());
        hrRepo.save(hrModel1);
        return new ResponseEntity<>(hrModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> loginDetails(String email, String password) {
        Optional<HrModel>hrModelOptional=hrRepo.findByEmailAndPassword(email,password);
        if (hrModelOptional.isPresent()){
            return new ResponseEntity<>("hr login success",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("email and password incorrect",HttpStatus.NOT_FOUND);
        }
    }
}
