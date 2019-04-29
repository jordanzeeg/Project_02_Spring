package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.java.util.LoggerSingleton;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            new BasicAWSCredentials("AKIAUSWONA5PAEPLQ2WH",
                    "k54uiCoV/a6+55OsBZQKJsf3JsGs4O3x+eFzHzus"
            );
    // BucketName for S3 service
    String bucketName = "faceyourbookspace";

    // Cofiguration to S3
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_2)
            .build();


    @PostMapping()
    public ResponseEntity<?> savePostImage(@RequestParam("post_id") int id,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam("file_name") String fileName) throws JsonProcessingException {

        // Key to reference post folder
        String key = "post_images/" + id + "/" + fileName;
        String URI = "Failed to save file";
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpeg");
        try {
            InputStream is = file.getInputStream();
            s3client.putObject(new PutObjectRequest(bucketName, key, is, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
            S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, key));
            URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();
        } catch (IOException e) {
            LoggerSingleton.getLogger().info("Failed to save file file");
            e.printStackTrace();
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(URI);
        return ResponseEntity.ok().body(json);
    }


    @GetMapping
    public ResponseEntity<?> getPostImages(@RequestParam("post_id") int id) throws JsonProcessingException {
        String URI;
        // Key to reference post folder
        String key;
        List<String> postImageKeyList = new ArrayList<>();
        List<String> postImageKeyListForId = new ArrayList<>();
        List<String> postImageURIList = new ArrayList<>();


        for(S3ObjectSummary summary : S3Objects.withPrefix(s3client, bucketName, "post_images/")) {
            key = summary.getKey();
            postImageKeyList.add(key);

        }
        for(int i = 0; i < postImageKeyList.size(); i++) {
            String s = postImageKeyList.get(i);
            if(s.contains("/" + id + "/")){
                postImageKeyListForId.add(s);
            }
        }

        for(int i = 0; i < postImageKeyListForId.size(); i++) {
            S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, postImageKeyListForId.get(i)));
            URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();
            postImageURIList.add(URI);
        }


        return ResponseEntity.ok().body(postImageURIList);
    }
}
