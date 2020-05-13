package com.sergiu.builders;

import com.sergiu.entity.Hall;

import java.util.List;

public class HallBuilder {

    public static Hall build(List<String> fields) {
        Hall hall = new Hall();
        hall.setName(String.valueOf(fields.get(0)));
        hall.setSize(Integer.valueOf(fields.get(1)));
        hall.setUtilizableSize(Integer.valueOf(fields.get(2)));
        return hall;
    }
}
