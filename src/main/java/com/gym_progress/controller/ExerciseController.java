package com.gym_progress.controller;

import com.gym_progress.model.Exercise;
import com.gym_progress.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/{exerciseId}/user/{userId}")
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise, @PathVariable Long userId){
        try{
            exerciseService.createExercise(exercise, userId);
            return ResponseEntity.ok(exercise);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllExercisesByUser(@PathVariable Long userId){
        try {
            List<Exercise> exercisesByUser = exerciseService.getExercisesByUser(userId);
            return ResponseEntity.ok(exercisesByUser);
        }catch ( RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e));
        }
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<?> getExerciseById(@PathVariable Long exerciseId){
        try{
            Exercise exercise = exerciseService.getExerciseById(exerciseId);
            return ResponseEntity.ok(exercise);
        } catch (RuntimeException e ){
            return ResponseEntity.badRequest().body(Map.of("message", e));
        }
    }

    @PutMapping("/{exerciseId}/user/{userId}")
    public ResponseEntity<?> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise, @PathVariable Long userId){
        try{
            Exercise updateExcercise = exerciseService.updateExercise(exercise, exerciseId, userId);
            return ResponseEntity.ok(updateExcercise);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e));
        }
    }

    @DeleteMapping("/{exerciseId}/user/{userId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long exerciseId, @PathVariable Long userId){
        try {
            exerciseService.deleteExercise(exerciseId, userId);
            return ResponseEntity.ok(Map.of("Message", "Dieta eliminda exitosamente"));
        } catch ( RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", "You can not delete the diet"));
        }
    }


}
