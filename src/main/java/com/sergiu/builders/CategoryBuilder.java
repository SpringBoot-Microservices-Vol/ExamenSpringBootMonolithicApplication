package com.sergiu.builders;

import com.sergiu.entity.Category;
import com.sergiu.util.AdmissionType;

import java.util.List;

public class CategoryBuilder {
    public static Category build(List<String> fields) {
        Category category = new Category();
        category.setName(String.valueOf(fields.get(0)));
        category.setDiscipline(String.valueOf(fields.get(1)));
        category.setLanguage(String.valueOf(fields.get(2)));
        category.setAdmissionType(AdmissionType.get(fields.get(3)));
        return category;
    }
}
