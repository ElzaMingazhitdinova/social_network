package com.example.testingrestdocs.service;


import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.createCategory(category);
    }

    public List<Post> GetCategoryWithPosts(Long id) {

        return postFinderService.findByCategory(id);
       // return this.storage.GetCategory(cID);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAllCategories();
    }

    public String subscribeToCategory(Category category) throws Exception {
        String status = "";
        if (!postFinderService.findByCategory(category.getId()).isEmpty()) {
            status = "OK";
        } else if (postFinderService.findByCategory(category.getId()).isEmpty()) {
            status = "NOT OK";
        }
        return status;
    }
}
