package com.example.movie.repository;

import com.example.movie.Entity.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankCustomerRepository extends JpaRepository<RankCustomer, Integer> {
}
