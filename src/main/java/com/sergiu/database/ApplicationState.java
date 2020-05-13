package com.sergiu.database;

import javax.persistence.*;

@Entity
public class ApplicationState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String isImportedResources;

    @Column
    private String isDistributed;

    @Column
    private String isDistributedFinalized;

    @Column
    private String isExamFinish;


    public ApplicationState() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsImportedResources() {
        return isImportedResources;
    }

    public void setIsImportedResources(String isImportedResources) {
        this.isImportedResources = isImportedResources;
    }

    public String getIsDistributed() {
        return isDistributed;
    }

    public void setIsDistributed(String isDistributed) {
        this.isDistributed = isDistributed;
    }

    public String getIsDistributedFinalized() {
        return isDistributedFinalized;
    }

    public void setIsDistributedFinalized(String isDistributedFinalized) {
        this.isDistributedFinalized = isDistributedFinalized;
    }

    public String getIsExamFinish() {
        return isExamFinish;
    }

    public void setIsExamFinish(String isExamFinish) {
        this.isExamFinish = isExamFinish;
    }
}
