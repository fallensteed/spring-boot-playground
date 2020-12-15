package com.hubertart.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

    @Bean
    public WordCounter getCounter(WordCountConfig config){
        return new WordCounter(config);
    }

}
