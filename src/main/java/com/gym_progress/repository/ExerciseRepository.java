package com.gym_progress.repository;

import com.gym_progress.model.Exercise;
import com.gym_progress.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByUser(User user);
    List<Exercise> findUserById(Long user);
}
