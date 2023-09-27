package com.team.unithonbackend.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.team.unithonbackend.aws.S3FileService;
import com.team.unithonbackend.domain.category.service.CategoryService;
import com.team.unithonbackend.domain.post.dto.PostIdImageUrlResponseDto;
import com.team.unithonbackend.domain.post.entity.Post;
import com.team.unithonbackend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final CategoryService categoryService;

    private final S3FileService s3FileService;

    public Page<PostIdImageUrlResponseDto> pagePostImageUrls(int id, int page, int pageSize){
        List<String> imageUrlList = s3FileService.listImageUrlsInCategory(id);
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        int startIndex = (page-1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, imageUrlList.size());
        List<PostIdImageUrlResponseDto> postIdImageUrlResponseDtos = Post.toPostIdImageUrlResponseDto(imageUrlList.subList(startIndex,endIndex));
        return new PageImpl<>(postIdImageUrlResponseDtos, pageable, postIdImageUrlResponseDtos.size());
    }

//      public Page<String> pagePostImageUrls(String category, int page, int pageSize){
//        List<String> imageUrlList = s3FileService.listImageUrlsInCategory(category);
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//
//
////        // Create a Page of image URLs using PageRequest
////        int startIndex = page * pageSize;
////        int endIndex = Math.min(startIndex + pageSize, imageUrlList.size());
////        List<String> pageContent = imageUrlList.subList(startIndex, endIndex);
//
//        return new PageImpl<>(imageUrlList, pageable, imageUrlList.size());
//    }

}
