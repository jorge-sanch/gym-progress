package com.gym_progress.service;

import com.gym_progress.model.Rol;
import com.gym_progress.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    public Rol getDefaultRol() {
        return rolRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Rol con ID 2 no encontrado"));
    }
}
