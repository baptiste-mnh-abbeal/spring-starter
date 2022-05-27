package com.abbeal.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.abbeal.demo.model.AppUser;
import com.abbeal.demo.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class AppUserController {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        Iterable<AppUser> appUserIt = this.appUserRepository.findAll();
        return StreamSupport.stream(appUserIt.spliterator(), false)
                .collect(Collectors.toList());
    }
}
