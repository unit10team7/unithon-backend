package com.team.unithonbackend.domain.post.entity;

import com.team.unithonbackend.domain.category.entity.Category;
import com.team.unithonbackend.domain.post.dto.PostIdImageUrlResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private int id;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private String imageUrl;

    @Column
    private String content;

    @Column(nullable = false)
    private int numberPeople;

    @Builder
    public Post(int id, Category category, String imageUrl, String content, int numberPeople) {
        this.id = id;
        this.category = category;
        this.imageUrl = imageUrl;
        this.content = content;
        this.numberPeople = numberPeople;
    }

//    public static List<PostIdImageUrlResponseDto> toPostIdImageUrlResponseDto(List<String> imageUrlList){
//        List<PostIdImageUrlResponseDto> list = new ArrayList<>();
//        for (int i=1; i<imageUrlList.size(); i++){
//            list.add(PostIdImageUrlResponseDto.builder()
//                    .id(i)
//                    .imageUrl(imageUrlList.get(i-1))
//                    .build());
//        }
//        return list;
//    }

//    public static List<PostIdImageUrlResponseDto> toPostIdImageUrlResponseDto(List<String> imageUrlList) {
//        return IntStream.range(0, imageUrlList.size())
//                .mapToObj(i -> PostIdImageUrlResponseDto.builder()
//                        .id(i + 1) // Adding 1 to i to start id from 1
//                        .imageUrl(imageUrlList.get(i))
//                        .build())
//                .collect(Collectors.toList());
//    }

    public static List<PostIdImageUrlResponseDto> toPostIdImageUrlResponseDto(List<String> imageUrlList) {
//        skip the first element in list cuz it is invalid like "imageUrl": "https://kpophots..amazonaws.com/ctg_boyfriend/"
        return IntStream.range(1, imageUrlList.size()) // Start from index 1
                .mapToObj(i -> PostIdImageUrlResponseDto.builder()
                        .id(i) // Use i as is (no need to add 1)
                        .imageUrl(imageUrlList.get(i))
                        .build())
                .collect(Collectors.toList());
    }
}
