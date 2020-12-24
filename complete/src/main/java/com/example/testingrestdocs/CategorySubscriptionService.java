package com.example.testingrestdocs;


import com.example.testingrestdocs.objects.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorySubscriptionService {
    @Autowired
    private PostFinderService postFinderService;

    public String subscribeToCategory(Category category) throws Exception {
        String status = "";
        if (!postFinderService.findByCategory(category.getName()).isEmpty()) {
            status = "OK";
        } else if (postFinderService.findByCategory(category.getName()).isEmpty()) {
            status = "NOT OK";
        }
        return status;
    }
}
