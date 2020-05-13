package com.sergiu.controller;

import com.sergiu.dto.HomeDTO;
import com.sergiu.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public HomeDTO getHomeView() {
        return homeService.getHomeView();
    }
}
