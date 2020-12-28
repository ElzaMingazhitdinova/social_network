package com.example.testingrestdocs.service;

import com.example.testingrestdocs.objects.Characteristic;
import com.example.testingrestdocs.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicService {
    @Autowired
    private CharacteristicRepository characteristicRepository;

    public void createCharasterictic(Characteristic characteristic){
        characteristicRepository.createCharasterictic(characteristic);
    }
}
