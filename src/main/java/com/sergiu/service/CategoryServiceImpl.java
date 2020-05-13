package com.sergiu.service;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.Category;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.CategoryRepository;
import com.sergiu.transformer.CategoriesTransformer;
import com.sergiu.util.AdmissionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRepository categoryRepository;
    private CandidateRepository candidateRepository;
    private CategoriesTransformer categoriesTransformer;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Autowired
    public void setCategoriesTransformer(CategoriesTransformer categoriesTransformer) {
        this.categoriesTransformer = categoriesTransformer;
    }

    @Override
    public List<Category> getAllCategoriesWithCandidates() {

        List<Category> categories = categoryRepository.findAllByAdmissionType(AdmissionType.ADMITERE);
        List<Category> result = new ArrayList<>();
        for (Category category : categories) {
            if (candidateRepository.findAllByCategory_Id(category.getId()).size() > 0) {
                category.setCandidateEntities(candidateRepository.findAllByCategory_Id(category.getId()));
                result.add(category);
            } else {

                LOGGER.info("Skipped the category with id [{}].", category.getId());
            }
        }
        return result;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoriesTransformer.toDTO(categoryRepository.findAll());
    }

    @Override
    public void add(CategoryDTO categoryDTO) {
        Category category = categoriesTransformer.toEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.save(categoriesTransformer.toEntity(categoryDTO));
        return categoriesTransformer.toDTO(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
