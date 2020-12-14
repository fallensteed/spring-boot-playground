package com.hubertart.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

    @Bean
    public WordCounter getCounter(){
        return new WordCounter();
    }

}
