package com.gym_progress.service;

import com.gym_progress.model.Diet;
import com.gym_progress.model.User;
import com.gym_progress.repository.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DietService {
    private final DietRepository dietRepository;
    private final UserService userService;



    // ✅ Crear dieta asignando automáticamente el usuario
    @Transactional
    public Diet createDiet(Diet diet, Long userId) {
        // Buscar el usuario
        User user = userService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        // Asignar el usuario a la dieta
        diet.setUser(user);

        // Guardar la dieta
        return dietRepository.save(diet);
    }

    // ✅ Obtener todas las dietas de un usuario por ID
    public List<Diet> getDietsByUserId(Long userId) {
        // Verificar que el usuario existe
        userService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        return dietRepository.findUserById(userId);
    }

    //Obtener todas las dietas de un usuario (pasando el objeto User)
    public List<Diet> getDietsByUser(User user) {
        return dietRepository.findByUser(user);
    }

    // Obtener dieta por ID
    public Diet getDietById(Long dietId) {
        return dietRepository.findById(dietId)
                .orElseThrow(() -> new RuntimeException("Dieta no encontrada con ID: " + dietId));
    }

    //Actualizar dieta
    @Transactional
    public Diet updateDiet(Long dietId, Diet dietDetails, Long userId) {
        Diet existingDiet = getDietById(dietId);

        // Verificar que la dieta pertenece al usuario
        if (!existingDiet.getUser().getId().equals(userId)) {
            throw new RuntimeException("No tienes permiso para modificar esta dieta");
        }

        // Actualizar campos
        existingDiet.setFoodTime(dietDetails.getFoodTime());
        existingDiet.setFood(dietDetails.getFood());
        existingDiet.setWater(dietDetails.getWater());
        existingDiet.setDay(dietDetails.getDay());
        existingDiet.setNotes(dietDetails.getNotes());

        return dietRepository.save(existingDiet);
    }

    // Eliminar dieta
    @Transactional
    public void deleteDiet(Long dietId, Long userId) {
        Diet diet = getDietById(dietId);

        // Verificar que la dieta pertenece al usuario
        if (!diet.getUser().getId().equals(userId)) {
            throw new RuntimeException("No tienes permiso para eliminar esta dieta");
        }

        dietRepository.delete(diet);
    }

    //Eliminar todas las dietas de un usuario
    @Transactional
    public void deleteAllDietsByUser(Long userId) {
        User user = userService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        List<Diet> diets = dietRepository.findByUser(user);
        dietRepository.deleteAll(diets);
    }
}