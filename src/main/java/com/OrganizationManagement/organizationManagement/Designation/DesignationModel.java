package com.OrganizationManagement.organizationManagement.Designation;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "designation_table")
@Data
public class DesignationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designationId")
    private Long designationId;

    @Column(name = "designationName")
    private String designationName;

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }
}
