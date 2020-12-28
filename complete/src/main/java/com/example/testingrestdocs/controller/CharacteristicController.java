package com.example.testingrestdocs.controller;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Characteristic;
import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.CategoryService;
import com.example.testingrestdocs.service.CharacteristicService;
import com.example.testingrestdocs.service.CommentAppenderService;
import com.example.testingrestdocs.service.PostFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CharacteristicController {
    @Autowired
    private CharacteristicService characteristicService;

    @PostMapping(value = "/characteristic/create")
    public void createCharac(@RequestBody Characteristic characteristic) {
        characteristicService.createCharasterictic(characteristic);
    }
}
