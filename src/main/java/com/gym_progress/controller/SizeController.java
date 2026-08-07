package com.gym_progress.controller;

import com.gym_progress.model.Size;
import com.gym_progress.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sizes")
public class SizeController {
    private final SizeService sizeService;

    @PostMapping("user/{userId}")
    public ResponseEntity<?> createSize(@PathVariable Long userId, @RequestBody Size size){
        try{
            Size createSize = sizeService.createSize(size, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createSize);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("mesagge", e.getMessage()));
        }
    }

    @GetMapping("/userId")
    public ResponseEntity<?> getAllSizessByUser(@PathVariable Long userId){
        try{
            List<Size> sizes = sizeService.getSizesByUserId(userId);
            return ResponseEntity.ok(sizes);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("mesagge", e.getMessage()));
        }
    }

    @GetMapping("/{sizeId}")
    ResponseEntity<?> getSizeById(Long sizeId){
        try{
            Size size = sizeService.getSizeById(sizeId);
            return ResponseEntity.ok(size);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{sizeId}/user/{userId}")
    public ResponseEntity<?> deleteDiet(@PathVariable Long sizeId, @PathVariable Long userId){
        try{
            sizeService.deleteSize(sizeId,userId);
            return ResponseEntity.ok(Map.of("message", "Dieta Size eliminado exitosamente"));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{sizeId}/user/{userId}")
    public ResponseEntity<?> updateSize( @PathVariable Long sizeId, @RequestBody Size size, @PathVariable Long userId){
        try{
            sizeService.updateSize(sizeId, size, userId);
            return ResponseEntity.ok(size);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }



}
