package com.example.loja_virtual_bff.infrastructure.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileLogger {

    private final Environment environment;

    public ProfileLogger(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void logActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Active profiles:");
        for (String profile : activeProfiles) {
            System.out.println("O PERFIL ATUAL É :" + profile);
        }
    }
}
