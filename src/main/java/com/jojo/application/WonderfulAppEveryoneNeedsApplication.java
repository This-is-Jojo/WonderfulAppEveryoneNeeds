package com.jojo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class WonderfulAppEveryoneNeedsApplication
{

    public static void main(String[] args)
    {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        SpringApplication.run(WonderfulAppEveryoneNeedsApplication.class, args);
    }

}

