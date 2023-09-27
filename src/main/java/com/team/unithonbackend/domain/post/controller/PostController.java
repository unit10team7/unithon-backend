package com.team.unithonbackend.domain.post.controller;

import com.team.unithonbackend.aws.S3FileService;
import com.team.unithonbackend.domain.post.dto.PostIdImageUrlResponseDto;
import com.team.unithonbackend.domain.post.service.PostService;
import com.team.unithonbackend.dto.MultiResponseDto;
import com.team.unithonbackend.dto.PageInfo;
import com.team.unithonbackend.dto.SingleResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
@Slf4j
@Validated
//@CrossOrigin(origins = "*",maxAge = 3600)
public class PostController {

    private final PostService postService;

    private final S3FileService s3FileService;

    @GetMapping("")
    public ResponseEntity<String> heartBeat(){
        return ResponseEntity.ok("heartbeat ok");
    }

    @GetMapping("/{category_id}")
    public ResponseEntity getPosts(@PathVariable("category_id") int id,
                                   @Positive @RequestParam(defaultValue = "1") int page,
                                   @Positive @RequestParam(defaultValue = "20") int size){
        Page<PostIdImageUrlResponseDto> postIdImageUrlResponseDtos = postService.pagePostImageUrls(id, page, size);

        //check how many pages there are for a given id
        List<String> imageUrlList = s3FileService.listImageUrlsInCategory(id);
        int totalElements = imageUrlList.size();
        int totalPages = (totalElements + size - 1) / size;
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
        List<PostIdImageUrlResponseDto> content = postIdImageUrlResponseDtos.getContent();
        return ResponseEntity.ok(new MultiResponseDto<>(content,pageInfo));
    }
}
