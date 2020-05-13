package com.sergiu.builders;

import com.sergiu.entity.Supervisor;

import java.util.List;

public class SupervisorBuilder {
    public static Supervisor build(List<String> fields) {
        Supervisor supervisor = new Supervisor();
        supervisor.setFirstName(String.valueOf(fields.get(0)));
        supervisor.setLastName(String.valueOf(fields.get(1)));
        return supervisor;
    }
}
