package com.OrganizationManagement.organizationManagement.Resource;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "resourceTable")
public class ResourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "resourceId")
    private Long resourceId;

//    @Column(name = "departmentId")
//    private Long departmentId;

    @Column(name = "resource")
    private String resource;

    @Column(name = "quantity")
    private Integer quantity;

//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
