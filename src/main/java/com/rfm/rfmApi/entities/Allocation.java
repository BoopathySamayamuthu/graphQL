package com.rfm.rfmApi.entities;

import com.rfm.rfmApi.models.AuditModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="allocation")
public class Allocation extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="allocationId")
    private int allocationId;

    @JoinColumn
    @Column(name = "rfmProjectId")
    private String rfmProjectId;

    @Column(name ="billRate")
    private double billRate;

    @Column(name = "allocationActiveStatus")
    private Boolean allocationActiveStatus;

    @JoinColumn
    @Column(name = "ctsEmpId")
    private String ctsEmpId;

    @Column(name ="hours")
    private int hours;

    @Column(name ="financialYear")
    private int financialYear;

    @Column(name = "allocationType")
    private String allocationType;

    @Column(name = "janAllocation")
    private double janAllocation;

    @Column(name ="febAllocation")
    private double febAllocation;

    @Column(name ="marAllocation")
    private double marAllocation;

    @Column(name ="aprAllocation")
    private double aprAllocation;

    @Column(name ="mayAllocation")
    private double mayAllocation;

    @Column(name ="junAllocation")
    private double junAllocation;

    @Column(name ="julAllocation")
    private double julAllocation;

    @Column(name ="augAllocation")
    private double augAllocation;

    @Column(name ="sepAllocation")
    private double sepAllocation;

    @Column(name ="octAllocation")
    private double octAllocation;

    @Column(name ="novAllocation")
    private double novAllocation;

    @Column(name ="decAllocation")
    private double decAllocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project projects;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resources;

}
