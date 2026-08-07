package com.gym_progress.repository;

import com.gym_progress.model.Diet;
import com.gym_progress.model.Size;
import com.gym_progress.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {


    List<Size>findByUser(User user);
    List<Size>findUserById(Long id);




}
