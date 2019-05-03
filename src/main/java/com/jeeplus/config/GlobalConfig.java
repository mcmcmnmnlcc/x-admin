package com.jeeplus.config;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
public class GlobalConfig {


    @Bean
    public Gson gson(){
        return new Gson();
    }
}
