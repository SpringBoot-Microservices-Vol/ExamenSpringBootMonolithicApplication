package com.sergiu.service;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategoriesWithCandidates();

    List<CategoryDTO> getAllCategories();

    void add(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

    void deleteCategory(Integer cnp);
}
