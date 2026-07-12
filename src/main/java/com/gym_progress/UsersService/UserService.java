package com.gym_progress.UsersService;

import com.gym_progress.model.Users;
import com.gym_progress.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id){
        return usersRepository.findById(id);
    }

    public Users saveUser(Users user){
        return usersRepository.save(user);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

    public Users updateUser(Long id, Users userUpdate){
        Optional<Users> user = usersRepository.findById(id);
        if(user.isEmpty()) throw new IllegalArgumentException("El usuario con el Id: " + id + " no existe");

        Users userOriginal = user.get();

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

