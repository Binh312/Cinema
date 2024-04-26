package com.example.movie.repository;

import com.example.movie.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("select f from Food f where f.nameOfFood like ?1 and f.isActive = true")
    Optional<Food> findByFoodName(String foodName);

    @Query("select f from Food f where f.isActive = true")
    List<Food> getAllFood();


}
