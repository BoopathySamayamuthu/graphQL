package com.rfm.rfmApi.entities;

import com.rfm.rfmApi.models.AuditModel;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "leaves")
public class Leaves extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaveId")
    private int leaveId;

    @JoinColumn
    @Column(name = "ctsEmpId")
    private String ctsEmpId;

    @Column(name = "appliedDate")
    private Timestamp appliedDate;

    @Column(name = "leaveStartDate")
    private Timestamp leaveStartDate;

    @Column(name = "leaveEndDate")
    private Timestamp leaveEndDate;

    @Column(name = "leaveDays")
    private double leaveDays;

    @Column(name = "leaveType")
    private String leaveType;

    @Column(name = "leaveStatus")
    private String leaveStatus;

    @Column(name = "isApproved")
    private boolean isApproved;

    @Column(name = "reason")
    private String reason;

    @Column(name = "financialYear")
    private int financialYear;

    @Column(name = "janLeaves")
    private double janLeaves;

    @Column(name = "febLeaves")
    private double febLeaves;

    @Column(name = "marLeaves")
    private double marLeaves;

    @Column(name = "aprLeaves")
    private double aprLeaves;

    @Column(name = "mayLeaves")
    private double mayLeaves;

    @Column(name = "junLeaves")
    private double junLeaves;

    @Column(name = "julLeaves")
    private double julLeaves;

    @Column(name = "augLeaves")
    private double augLeaves;

    @Column(name = "sepLeaves")
    private double sepLeaves;

    @Column(name = "octLeaves")
    private double octLeaves;

    @Column(name = "novLeaves")
    private double novLeaves;

    @Column(name = "decLeaves")
    private double decLeaves;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resources;

}
