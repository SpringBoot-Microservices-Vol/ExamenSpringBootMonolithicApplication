package com.sergiu.model;

public class AllocationModel {
    Integer roBuget;
    Integer roTaxa;
    Integer enBuget;
    Integer enTaxa;
    Integer mdRoBuget;
    Integer mdEnBuget;

    public AllocationModel(Integer roBuget, Integer roTaxa, Integer enBuget, Integer enTaxa, Integer mdRoBuget, Integer mdEnBuget) {
        this.roBuget = roBuget;
        this.roTaxa = roTaxa;
        this.enBuget = enBuget;
        this.enTaxa = enTaxa;
        this.mdRoBuget = mdRoBuget;
        this.mdEnBuget = mdEnBuget;
    }

    public Integer getRoBuget() {
        return roBuget;
    }

    public Integer getRoTaxa() {
        return roTaxa;
    }

    public Integer getEnBuget() {
        return enBuget;
    }

    public Integer getEnTaxa() {
        return enTaxa;
    }

    public Integer getMdRoBuget() {
        return mdRoBuget;
    }

    public Integer getMdEnBuget() {
        return mdEnBuget;
    }

    public void decrementRoBuget() {
        this.roBuget = this.roBuget - 1;
    }

    public void decrementRoTaxa() {
        this.roTaxa = this.roTaxa - 1;
    }

    public void decrementEnBuget() {
        this.enBuget = this.enBuget - 1;
    }

    public void decrementEnTaxa() {
        this.enTaxa = this.enTaxa - 1;
    }

    public void decrementMdRoBuget() {
        this.mdRoBuget = this.mdRoBuget - 1;
    }

    public void decrementMdEnBuget() {
        this.mdEnBuget = this.mdEnBuget - 1;
    }
}
