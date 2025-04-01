package com.OrganizationManagement.organizationManagement.DepResource;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "depresource_table")
public class DepResModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepResourceId")
    private Long DepResourceId;

    @Column(name = "DepResource")
    private String DepResource;

    @Column(name = "quantity")
    private Long quantity;

    public Long getDepResourceId() {
        return DepResourceId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setDepResourceId(Long depResourceId) {
        DepResourceId = depResourceId;
    }

    public String getDepResource() {
        return DepResource;
    }

    public void setDepResource(String depResource) {
        DepResource = depResource;
    }
}
