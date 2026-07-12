package com.gym_progress.UsersService;

import com.gym_progress.model.Size;
import com.gym_progress.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SizeService {
    public SizeRepository sizeRepository;

    public Size saveSize (Size size){
        return sizeRepository.save(size);
    }

    public void deleteSize (Long id){
        sizeRepository.deleteById(id);
    }

    public Size updateSize(Long id, Size sizeUpdate){
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isEmpty()) throw new IllegalArgumentException("El size con el id "+ id + "no existe");

        Size sizeOriginal =  size.get();

        if (sizeUpdate.getDate() != null) sizeOriginal.setDate(sizeUpdate.getDate());
        if (sizeUpdate.getWeight() != null) sizeOriginal.setWeight(sizeUpdate.getWeight());
        if  (sizeUpdate.getNotes() != null) sizeOriginal.setNotes(sizeUpdate.getNotes());

        return  sizeRepository.save(sizeOriginal);
    }


}
