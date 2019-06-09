package com.example.springbootend.controller;


import com.example.springbootend.service.LessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/lession")
public class LessionController {
    @Autowired
    private LessionService lessionService;

    @GetMapping("/list")
    public Map getLessions(){
        return Map.of("lessions",lessionService.findAllLessions());
    }
}
