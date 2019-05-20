package ru.bellintegrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Старт Spring приложения
 */
@SpringBootApplication
public class CpDirApplication extends SpringBootServletInitializer {
    /** Точка старта приложения
     * @param args аргументы строки
     */
    public static void main(String[] args) {
            SpringApplication.run(CpDirApplication.class, args);
        }
}
