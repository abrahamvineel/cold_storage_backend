package org.fileupload.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@AllArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

    public S3Service(@Value("${s3.endpoint}") String endpoint,
                     @Value("${s3.accessKey}") String accessKey,
                     @Value("${s3.secretKey}") String secretKey,
                     @Value("${s3.region}") String region) {

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .endpointOverride(java.net.URI.create(endpoint))
                .build();
    }
}
