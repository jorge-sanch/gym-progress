package com.gym_progress.config;


import com.gym_progress.model.Rol;
import com.gym_progress.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepository) {
        return args -> {
            // Crear roles si no existen
            if (rolRepository.count() == 0) {
                Rol adminRol = new Rol();
                adminRol.setId(1L);
                adminRol.setName("ADMIN");
                adminRol.setDescription("Administrador del sistema");
                rolRepository.save(adminRol);

                Rol userRol = new Rol();
                userRol.setId(2L);
                userRol.setName("USER");
                userRol.setDescription("Usuario normal");
                rolRepository.save(userRol);

            }
        };
    }
}

