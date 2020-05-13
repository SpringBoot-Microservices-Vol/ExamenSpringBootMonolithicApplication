package com.sergiu.builders;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.Category;

import java.util.List;

public class CandidateBuilder {
    public static Candidate build(List<String> fieldsValue) {
        Candidate candidate = new Candidate();
        Category category = new Category();
        candidate.setCnp(Long.valueOf(fieldsValue.get(0)));
        candidate.setFirstName(String.valueOf(fieldsValue.get(1)));
        candidate.setLastName(String.valueOf(fieldsValue.get(2)));
        category.setId(Integer.valueOf(fieldsValue.get(3)));
        candidate.setCategory(category);
        candidate.setHighSchool(String.valueOf(fieldsValue.get(4)));
        candidate.setBacBestGrade(Double.valueOf(fieldsValue.get(5)));
        candidate.setBacGrade(Double.valueOf(fieldsValue.get(6)));
        return candidate;
    }
}
