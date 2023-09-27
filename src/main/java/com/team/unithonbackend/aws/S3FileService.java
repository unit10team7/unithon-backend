package com.team.unithonbackend.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.team.unithonbackend.domain.category.repository.CategoryRepository;
import com.team.unithonbackend.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class S3FileService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final CategoryService categoryService;

    public String upload(MultipartFile multipartFile) throws IOException {
        //for saved file in s3 to have unique names via UUID.randomUUID()
        String s3FileName = multipartFile.getOriginalFilename();

        //inform file's size via ContentLength to S3S3FileController
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        //s3 api method to open file stream and upload it to S3
        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    public String getImageUrl(String imageName){
        URL url = amazonS3.getUrl("kpop4shots", imageName);
        return url.toString() + ".jpg";
    }

    public List<String> listImageUrlsInCategory(int id) {
        // Assuming "kpop4shots" is your S3 bucket name
        String bucketName = "kpop4shots";
        String category = "";

        if(id==1){
            category = "ctg_boyfriend";
        } else {
            category = categoryService.findS3NameById(id);
        }
        List<String> imageUrls = new ArrayList<>();

        // Specify the prefix to filter objects in the specific category
        String prefix = category + "/"; // Assuming images are stored under "category/" in S3

        ListObjectsV2Request listRequest = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(prefix);

        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(listRequest);

        for (S3ObjectSummary objectSummary : listObjectsV2Result.getObjectSummaries()) {
            // Generate the URL for each object (image) in the category
            String imageUrl = amazonS3.getUrl(bucketName, objectSummary.getKey()).toString();
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }

//    public List<String> listImageUrlsInCategory(String category) {
//        // Assuming "kpop4shots" is your S3 bucket name
//        String bucketName = "kpop4shots";
//
//        List<String> imageUrls = new ArrayList<>();
//
//        // Specify the prefix to filter objects in the specific category
//        String prefix = category + "/"; // Assuming images are stored under "category/" in S3
//
//        ListObjectsV2Request listRequest = new ListObjectsV2Request()
//                .withBucketName(bucketName)
//                .withPrefix(prefix);
//
//        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(listRequest);
//
//        for (S3ObjectSummary objectSummary : listObjectsV2Result.getObjectSummaries()) {
//            // Generate the URL for each object (image) in the category
//            String imageUrl = amazonS3.getUrl(bucketName, objectSummary.getKey()).toString();
//            imageUrls.add(imageUrl);
//        }
//        return imageUrls;
//    }


}
