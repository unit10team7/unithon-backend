package com.team.unithonbackend.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "response DTO for post id and image url")
@Builder
public class PostIdImageUrlResponseDto {
    private int id;

    private String imageUrl;
}
