package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int size;

    private int utilizableSize;

    @JsonManagedReference("hall")
    @OneToMany
    @JoinTable(name = "distribution", joinColumns = {@JoinColumn(name = "id_hall")}, inverseJoinColumns = {
            @JoinColumn(name = "cnp_candidate")})
    private List<Candidate> listCandidates;


    public Hall() {
        this.listCandidates = new ArrayList<>();
    }

    public Hall(String name, int size, int utilizableSize) {
        this.name = name;
        this.size = size;
        this.utilizableSize = utilizableSize;
        this.listCandidates = new ArrayList<>();
    }

    public Hall(Integer id, String name, int size, int utilizableSize) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.utilizableSize = utilizableSize;
        this.listCandidates = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUtilizableSize() {
        return utilizableSize - getListCandidates().size();
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

    public List<Candidate> getListCandidates() {
        return listCandidates;
    }

    public void setListCandidates(List<Candidate> listCandidates) {
        this.listCandidates = listCandidates;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;
        result = prime * result + utilizableSize;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hall other = (Hall) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size != other.size)
            return false;
        if (utilizableSize != other.utilizableSize)
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Hall hall = (Hall) o;

        return (this.utilizableSize - listCandidates.size()) -
                (hall.getUtilizableSize() - hall.getListCandidates().size());
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", utilizableSize=" + utilizableSize +
                '}';
    }
}
