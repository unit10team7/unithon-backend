package com.team.unithonbackend.domain.category.service;

import com.team.unithonbackend.domain.category.entity.Category;
import com.team.unithonbackend.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String findS3NameById(int id){
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("no such id");
        } else return optional.get().getS3_name();
    }
}
