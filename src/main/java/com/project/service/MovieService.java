package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

@Component
public class MovieService {

    @Autowired
    private AwsCredentialsProvider awsCredentialsProvider;
    public String getMovieURL(String movieName){
         String key= "movies/Tom&Jerry.mp4";
         String bucketName="netflix-bucket-demo";




        S3Presigner presigner= S3Presigner
                .builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
                //awsCredentialsProvider.resolveCredentials();


        GetObjectRequest getObjectRequest= GetObjectRequest
                .builder()
                .key(key)
                .bucket(bucketName)
                .build();

        GetObjectPresignRequest getObjectPresignRequest=GetObjectPresignRequest
                .builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest request=presigner.presignGetObject(getObjectPresignRequest);



        return request.url().toString() ;
    }
}
