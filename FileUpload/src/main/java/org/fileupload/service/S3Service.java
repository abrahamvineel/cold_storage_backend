package org.fileupload.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class S3Service {

    private S3Client s3Client;

    private S3Presigner s3Presigner;

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

        this.s3Presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .endpointOverride(java.net.URI.create(endpoint))
                .build();
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_"
                    + file.getOriginalFilename();

            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                    .build(), RequestBody.fromBytes(file.getBytes()));
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generatePreSignedUrl(String fileName) {
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(b -> b.bucket(bucketName).key(fileName))
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }
}
