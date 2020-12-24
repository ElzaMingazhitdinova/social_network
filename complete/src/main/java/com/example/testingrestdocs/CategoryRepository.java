package com.example.testingrestdocs;

import com.example.testingrestdocs.objects.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepository {
    private Map<Long, Category> categoryMap;
    private Map<String, Long> nameIndexMap;

    public CategoryRepository() {
        this.categoryMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void createCategory(Category category) {
        categoryMap.put(category.getId(), category);
        nameIndexMap.put(category.getName(), category.getId());
    }

    public Category findCategory(String categoryName) {
        return categoryMap.get(nameIndexMap.get(categoryName));
    }
}
