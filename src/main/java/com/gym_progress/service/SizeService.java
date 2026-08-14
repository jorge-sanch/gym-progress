package com.gym_progress.service;

import com.gym_progress.model.Size;
import com.gym_progress.model.User;
import com.gym_progress.repository.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;
    private final UserService userService;

    @Transactional
    public Size createSize(Size size, Long userId){

        User user = userService.findByUserId(userId).orElseThrow(
                (()->new IllegalArgumentException("User not fouded")));

        size.setUser(user);

        return sizeRepository.save(size);

    }

    @Transactional
    public List<Size> getSizesByUserId(Long userId){
        userService.findByUserId(userId).orElseThrow(
                () ->new IllegalArgumentException("User not founded"));

        return sizeRepository.findUserById(userId);
    }

    @Transactional
    public Size getSizeById(Long sizeId){
        return sizeRepository.findById(sizeId).orElseThrow(
                ()-> new IllegalArgumentException("Diet not founded"));
    }

    @Transactional
    public Size updateSize(Long sizeId, Size sizeNewDetails, Long userId){
        Size existingSize = getSizeById(sizeId);

        if (!existingSize.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("You are not allowed to change this");
        }
        existingSize.setWeightGoal(sizeNewDetails.getWeightGoal());
        existingSize.setDate(sizeNewDetails.getDate());
        existingSize.setWeight(sizeNewDetails.getWeight());
        existingSize.setNotes(sizeNewDetails.getNotes());
        existingSize.setMainObjetive(sizeNewDetails.getMainObjetive());
        existingSize.setWeightGoal(sizeNewDetails.getWeightGoal());
        return sizeRepository.save(existingSize);
    }

    @Transactional
    public void deleteSize(Long sizeId, Long userId){
        Size size = getSizeById(sizeId);
        if (!size.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("You are not allowed to delete this");
        }
       sizeRepository.delete(size);
    }
    }



    //

