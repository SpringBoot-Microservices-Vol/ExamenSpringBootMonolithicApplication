package com.sergiu.controller;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class CategoriesController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/categories")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.add(categoryDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable(value = "id") Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Integer cnp) {
        categoryService.deleteCategory(cnp);
        return ResponseEntity.ok().build();
    }
}
