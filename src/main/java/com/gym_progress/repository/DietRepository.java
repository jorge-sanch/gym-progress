package com.gym_progress.repository;

import com.gym_progress.model.Diet;
import com.gym_progress.model.User;
import com.gym_progress.service.DietService;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findByUser(User user);
    List<Diet> findUserById(Long id);

    List<Diet> findByUserOrderByFoodTimeAsc(User user);
}
