package com.example.springboot.config;

import com.example.springboot.config.dao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    MemberRepository repository;

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.printf("dsf");
        repository.findAll();


    }
}
