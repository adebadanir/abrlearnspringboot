package com.juaracoding.abrlearnspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // Bisa tambah ThreadPool kalau mau (optional)
}
