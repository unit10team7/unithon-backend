package com.team.unithonbackend.domain.post.repository;

import com.team.unithonbackend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
