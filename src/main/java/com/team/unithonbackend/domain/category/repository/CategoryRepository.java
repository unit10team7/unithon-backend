package com.team.unithonbackend.domain.category.repository;

import com.team.unithonbackend.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findById(int id);
}
