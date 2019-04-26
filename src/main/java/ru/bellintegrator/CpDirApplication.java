package ru.bellintegrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CpDirApplication extends SpringBootServletInitializer {

        public static void main(String[] args) {
            SpringApplication.run(CpDirApplication.class, args);
        }
}
