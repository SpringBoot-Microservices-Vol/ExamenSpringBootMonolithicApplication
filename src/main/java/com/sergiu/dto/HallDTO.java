package com.sergiu.dto;

import java.io.Serializable;
import java.util.List;

public class HallDTO implements Serializable {

    private int id;

    private String name;

    private int utilizableSize;

    private int size;

    private List<CandidateDTO> listCandidates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUtilizableSize() {
        return utilizableSize;
    }

    public void setUtilizableSize(int utilizableSize) {
        this.utilizableSize = utilizableSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<CandidateDTO> getListCandidates() {
        return listCandidates;
    }

    public void setListCandidates(List<CandidateDTO> listCandidates) {
        this.listCandidates = listCandidates;
    }
}
