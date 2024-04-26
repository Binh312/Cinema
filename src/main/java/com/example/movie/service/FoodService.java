package com.example.movie.service;

import com.example.movie.Entity.Food;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food addFood(Food food){
        Optional<Food> foodOptionalName = foodRepository.findByFoodName(food.getNameOfFood());
        if (foodOptionalName.isPresent()){
            throw new MessageResponse("Tên món ăn đã tồn tại");
        }

        food.setIsActive(true);

        return foodRepository.save(food);
    }

    public Food updateFood(Food food){
        Optional<Food> foodOptionalId = foodRepository.findById(food.getId());
        if (foodOptionalId.isEmpty()){
            throw new MessageResponse("Món ăn không tồn tại");
        }
        Optional<Food> foodOptionalName = foodRepository.findByFoodName(food.getNameOfFood());
        if (foodOptionalName.isPresent()){
            throw new MessageResponse("Tên món ăn đã tồn tại");
        }

        food.setIsActive(foodOptionalId.get().getIsActive());

        return foodRepository.save(food);
    }

    public Food deleteFood(Integer foodId){
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isEmpty()){
            throw new MessageResponse("Món ăn không tồn tại");
        }

        foodOptional.get().setIsActive(false);
        return foodRepository.save(foodOptional.get());
    }

    public List<Food> getAllFood(){
        return foodRepository.getAllFood();
    }
}
