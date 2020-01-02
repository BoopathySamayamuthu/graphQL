package com.rfm.rfmApi.entities;


import com.rfm.rfmApi.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="project")
public class Project extends AuditModel {
    @Id
    @Column(name = "rfmProjectId")
    private String rfmProjectId;

    @Column(name="esaProjectId")
    private @NotNull String esaProjectId;

    @Column(name="esaProjectName")
    private String esaProjectName;

    @Column(name="accountName")
    private String accountName;

    @Column(name="sowProjectId")
    private String sowProjectId;

    @Column(name="sowProjectName")
    private String sowProjectName;

    @Column(name = "projectActiveStatus")
    private Boolean projectActiveStatus;

    @Column(name="department")
    private String department;

    @Column(name = "projectType")
    private String projectType;

    @Column(name="sbu")
    private String sbu;


}
