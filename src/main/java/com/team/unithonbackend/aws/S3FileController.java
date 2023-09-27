package com.team.unithonbackend.aws;

import com.amazonaws.services.s3.AmazonS3;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
@RequestMapping("/s3")
@RestController
public class S3FileController {
    private final S3FileService s3FileService;

    private final AmazonS3 s3Client;

    @GetMapping("")
    public ResponseEntity<String> heartBeat(){
        return ResponseEntity.ok("heartbeat ok");
    }

    @Operation(summary = "upload file to S3")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile)
            throws IOException{
        return ResponseEntity.ok(s3FileService.upload(multipartFile));
    }

    @Operation(summary = "delete uploaded file from S3")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFile(@RequestParam String fileName){
        //fileName to be modified to under member username
        s3FileService.deleteFile(fileName);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "get image url")
    @GetMapping("/get/{post-id}")
    public ResponseEntity<String> getPostImage(@PathVariable("post-id") String postId) {
//        String imageUrl = s3FileService.getImageUrl(postId);
//        return ResponseEntity.ok(imageUrl);
        URL url = s3Client.getUrl("kpop4shots", postId);
        return ResponseEntity.ok(url.toString() + ".jpg");
    }

    @Operation(summary = "get image url according to category")
    @GetMapping("/get/{category}/{post-id}")
    public ResponseEntity<String> getImageAccToCategory(@PathVariable("post-id") String postId,
                                                        @PathVariable("category") String category) {
        String imageUrl = s3FileService.getImageUrl(category + "/" + postId);
        return ResponseEntity.ok(imageUrl);
    }
}
