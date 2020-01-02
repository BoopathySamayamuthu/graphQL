package com.rfm.rfmApi.entities;

import com.rfm.rfmApi.models.AuditModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fixedcost")
public class FixedCost extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @JoinColumn
    @Column(name = "rfmProjectId")
    private String rfmProjectId;

    @Column(name = "financialYear")
    private int financialYear;

    @Column(name = "janCost")
    private double janCost;

    @Column(name = "febCost")
    private double febCost;

    @Column(name = "marCost")
    private double marCost;

    @Column(name = "aprCost")
    private double aprCost;

    @Column(name = "mayCost")
    private double mayCost;

    @Column(name = "junCost")
    private double junCost;

    @Column(name = "julCost")
    private double julCost;

    @Column(name = "augCost")
    private double augCost;

    @Column(name = "sepCost")
    private double sepCost;

    @Column(name = "octCost")
    private double octCost;

    @Column(name = "novCost")
    private double novCost;

    @Column(name = "decCost")
    private double decCost;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project projects;

}
