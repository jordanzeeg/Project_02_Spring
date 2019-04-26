package com.java.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;


@RestController
@RequestMapping("profile_image")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileImageController {

    // Credentials for S3 TODO: - figure out way not to hardcode this in controller
    AWSCredentials credentials =
            new BasicAWSCredentials("",
                    ""
            );
    // BucketName for S3 service
    String bucketName = "faceyourbookspace";

    // Cofiguration to S3
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_2)
            .build();


    @PostMapping("upload")
    public ResponseEntity<?> saveProfilePic(@RequestParam("username") String username,
                                            @RequestParam("file") File file) {
        // Key to get specific user's folder
        String key = "user_profile_pic/" + username + "/profile_pic";

        // boolean to determine if user folder exists
        boolean exists = s3client.doesObjectExist(bucketName, key);

        // Check if folder exists, if so delete it and replace it with new one (changing profle pic) else create a
        // file with profile pic
        if (exists) {
            s3client.deleteObject(bucketName, key);
            s3client.putObject(new PutObjectRequest(bucketName, key, file).withCannedAcl(CannedAccessControlList.PublicRead));
        } else {
            s3client.putObject(new PutObjectRequest(bucketName, key, file).withCannedAcl(CannedAccessControlList.PublicRead));
        }

        // Get object from file
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, key));

        // Get URI of object from AWS
        String URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();

        return ResponseEntity.ok().body(URI);
    }

    @GetMapping("image")
    public ResponseEntity<?> getProfilePicByUsername(@RequestParam("username") String username) {
        // Key to get specific user's folder
        String key = "user_profile_pic/" + username + "/profile_pic";
        String defaultKey = "user_profile_pic/default/default.jpg";

        // boolean to determine if user folder exists
        boolean exists = s3client.doesObjectExist(bucketName, key);

        // Check if folder exists, if so delete it and replace it with new one (changing profle pic) else create a
        // file with profile pic
        if (exists) {
            // Get object from file
            S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, key));
            // Get URI of object from AWS
            String URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();

            return ResponseEntity.ok().body(URI);

        } else {
            S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, defaultKey));
            // Get URI of object from AWS
            String URI = s3Object.getObjectContent().getHttpRequest().getURI().toString();

            return ResponseEntity.ok().body(URI);
        }

    }
}
