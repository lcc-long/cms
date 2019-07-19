package com.lcc.basic.test;

import java.util.HashSet;
import java.util.Set;

public class Dept {
    private int id;
    private String name;
    private Set<Staff> staffSet = new HashSet<Staff>();

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

    public Set<Staff> getStaffSet() {
        return staffSet;
    }

    public void setStaffSet(Set<Staff> staffSet) {
        this.staffSet = staffSet;
    }
}
