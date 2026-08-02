package com.gym_progress.UsersService;

import com.gym_progress.model.User;
import com.gym_progress.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository usersRepository;

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return usersRepository.findById(id);
    }

    public User saveUser(User user){
        return usersRepository.save(user);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

    public User updateUser(Long id, User userUpdate){
        Optional<User> user = usersRepository.findById(id);
        if(user.isEmpty()) throw new IllegalArgumentException("El usuario con el Id: " + id + " no existe");

        User userOriginal = user.get();

        if(userUpdate.getName() != null){
            userOriginal.setName(userUpdate.getName());
        }
        if(userUpdate.getBirthday() != null){
            userOriginal.setBirthday(userUpdate.getBirthday());
        }
        if(userUpdate.getPassword() != null){
            userOriginal.setPassword(userUpdate.getPassword());
        }
        return usersRepository.save(userOriginal);
        }

    }

