package com.java.controller;

import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/post_image")
public class PostImageController {
    // Credentials for S3 TODO: - figure out way not to hardcode this in controller
    AWSCredentials credentials =
            new BasicAWSCredentials("AKIAUSWONA5PAOG6MZGL",
                    "mD2xvUOZLZtnFIenWjTNnBtmBJvAMbLSOmnsd5D4"
            );
    // BucketName for S3 service
    String bucketName = "faceyourbookspace";

    // Cofiguration to S3
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_2)
            .build();


    @PostMapping("/upload")
    public ResponseEntity<?> savePostImage(@RequestParam("post_id") int id,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam("file_name") String fileName) {

        // Key to reference post folder
        String key = "post_images/" + id + "/" + fileName + ".jpg";

        ObjectMetadata objectMetadata = new ObjectMetadata();


        try {
            s3client.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get object from file
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, key));

        // Get URI of object from AWS
        String URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();

        return ResponseEntity.ok().body(URI);
    }
}
