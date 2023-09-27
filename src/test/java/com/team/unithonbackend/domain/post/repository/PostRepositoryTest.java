package com.team.unithonbackend.domain.post.repository;

import com.team.unithonbackend.domain.category.entity.Category;
import com.team.unithonbackend.domain.post.entity.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {

    @Mock
    private PostRepository postRepository;

    @Test
    public void testSavePost() {
        // Create a sample Post object
        Category category = Category.builder()
                .id(2)
                .name("nct")
                .s3_name("cgt_sdas")
                .build();

        Post post = Post.builder()
                .category(category)
                .imageUrl("sampleImageUrl")
                .content("Sample content")
                .numberPeople(42)
                .build();

        // Mock the save method of the PostRepository
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // Call the save method of the PostRepository
        Post savedPost = postRepository.save(post);

        // Verify that the save method was called and the returned Post matches our sample Post
        verify(postRepository, times(1)).save(post);
        assertEquals(post, savedPost);
    }
}