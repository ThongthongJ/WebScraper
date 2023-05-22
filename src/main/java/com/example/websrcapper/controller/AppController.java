package com.example.websrcapper.controller;

import com.example.websrcapper.entity.MsSetEntity;
import com.example.websrcapper.model.ResponseModel;
import com.example.websrcapper.services.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/insert/data") //body Object
    public void insertData(
    ) {
        this.appService.insertData();
    }

}