package com.example.movie.controller;

import com.example.movie.Entity.Food;
import com.example.movie.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/admin/add-food")
    public ResponseEntity<?> addFood(@RequestBody Food food){
        Food addFood = foodService.addFood(food);
        return new ResponseEntity<>(addFood, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-food")
    public ResponseEntity<?> updateFood(@RequestBody Food food){
        Food upadteFood = foodService.updateFood(food);
        return new ResponseEntity<>(upadteFood, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-food")
    public ResponseEntity<?> deleteFood(@RequestParam Integer foodId){
        Food deleteFood = foodService.deleteFood(foodId);
        return new ResponseEntity<>(deleteFood, HttpStatus.OK);
    }

    @GetMapping("/all/get-all-food")
    public ResponseEntity<?> getAllFood(){
        List<Food> foods = foodService.getAllFood();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
