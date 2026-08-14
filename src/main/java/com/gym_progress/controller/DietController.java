package com.gym_progress.controller;

import com.gym_progress.model.Diet;
import com.gym_progress.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diets")
public class DietController {
    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    // ✅ Crear una nueva dieta para un usuario específico
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createDiet(@PathVariable Long userId, @RequestBody Diet diet) {
        try {
            Diet createdDiet = dietService.createDiet(diet, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDiet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // ✅ Obtener todas las dietas de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDietsByUser(@PathVariable Long userId) {
        try {
            List<Diet> diets = dietService.getDietsByUserId(userId);
            return ResponseEntity.ok(diets);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // ✅ Obtener una dieta específica por ID
    @GetMapping("/{dietId}")
    public ResponseEntity<?> getDietById(@PathVariable Long dietId) {
        try {
            Diet diet = dietService.getDietById(dietId);
            return ResponseEntity.ok(diet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Actualizar una dieta
    @PutMapping("/{dietId}/user/{userId}")
    public ResponseEntity<?> updateDiet(@PathVariable Long dietId,
                                        @PathVariable Long userId,
                                        @RequestBody Diet diet) {
        try {
            Diet updatedDiet = dietService.updateDiet(dietId, diet, userId);
            return ResponseEntity.ok(updatedDiet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    //0 Eliminar una dieta
    @DeleteMapping("/{dietId}/user/{userId}")
    public ResponseEntity<?> deleteDiet(@PathVariable Long dietId, @PathVariable Long userId) {
        try {
            dietService.deleteDiet(dietId, userId);
            return ResponseEntity.ok(Map.of("message", "Dieta eliminada exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Eliminar todas las dietas de un usuario
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAllDietsByUser(@PathVariable Long userId) {
        try {
            dietService.deleteAllDietsByUser(userId);
            return ResponseEntity.ok(Map.of("message", "Todas las dietas del usuario eliminadas exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}