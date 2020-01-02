package com.rfm.rfmApi.entities;

import com.rfm.rfmApi.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="resource")
public class Resource extends AuditModel {

    @Id
    @Column(name="ctsEmpId")
    private String ctsEmpId;

    @Column(name="clientEmpId")
    private String clientEmpId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name = "ctsDesignation")
    private String ctsDesignation;

    @Column(name="ctsDepartment")
    private String ctsDepartment;

    @Column(name = "clientRole")
    private String clientRole;

    @Column(name = "primarySkill")
    private String primarySkill;

    @Column(name="location")
    private String location;

    @Column(name = "office")
    private String office;

    @Column(name = "activeStatus")
    private Boolean activeStatus;

}
