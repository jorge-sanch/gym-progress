package com.gym_progress.service;

import com.gym_progress.model.Exercise;
import com.gym_progress.model.User;
import com.gym_progress.repository.ExerciseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserService userService;

    @Transactional
    public Exercise createExercise (Exercise exercise, Long userId){
        User user = userService.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("User not founded"));

        exercise.setUser(user);
        return exerciseRepository.save(exercise);
    }

    @Transactional
    public List<Exercise> getExercisesByUser(Long userId){
        User user = userService.findByUserId(userId).orElseThrow(
                () ->new IllegalArgumentException("User not founded")
        );

        return exerciseRepository.findUserById(userId);
    }

    @Transactional
    public Exercise getExerciseById(Long exerciseId){
        return exerciseRepository.findById(exerciseId).orElseThrow(
                () -> new IllegalArgumentException("Exercise not founded"));
    }

    @Transactional
    public Exercise updateExercise(Exercise exercise, Long exerciseId, Long userId){
         User user = userService.findByUserId(userId).orElseThrow(
                 () -> new IllegalArgumentException("User not founded"));

         Exercise existingExercise = getExerciseById(exerciseId);

         if (!existingExercise.getUser().getId().equals(userId)){
             throw  new IllegalArgumentException("You're not the owner of this exercise");
         }

         if (!existingExercise.getExerciseName().matches(exercise.getExerciseName())) {existingExercise.setExerciseName(exercise.getExerciseName());}
        if (!existingExercise.getWeight().equals(exercise.getWeight())){existingExercise.setWeight(exercise.getWeight());}
        if (!existingExercise.getBodyPart().matches(exercise.getBodyPart())){existingExercise.setBodyPart(exercise.getBodyPart());}
        if (!existingExercise.getRepetitions().equals(exercise.getRepetitions())){existingExercise.setRepetitions(exercise.getRepetitions());}


         return exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(Long exerciseId, Long userId){
        Exercise exercise = getExerciseById(exerciseId);
        if(!exercise.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("You can not delete this");
        }
        exerciseRepository.deleteById(exerciseId);
    }
}
