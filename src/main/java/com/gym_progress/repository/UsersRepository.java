package com.gym_progress.repository;

import com.gym_progress.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
}
