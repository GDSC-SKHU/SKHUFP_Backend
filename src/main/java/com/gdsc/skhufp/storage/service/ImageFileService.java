package com.gdsc.skhufp.storage.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gdsc.skhufp.common.exception.storage.CouldNotSaveFileInS3;
import com.gdsc.skhufp.common.exception.storage.ImageFIleNotFoundException;
import com.gdsc.skhufp.storage.domain.model.ImageFile;
import com.gdsc.skhufp.storage.domain.repository.ImageFileRepository;
import com.gdsc.skhufp.storage.dto.ImageFileDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;
    private AmazonS3 amazonS3;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.region.static}")
    private String region;

    @Transactional
    public ImageFileDTO save(MultipartFile file) {
        String convertedName = createRandomFileName(file.getOriginalFilename());
        String s3Url = uploadToS3(file, "skhufp/" + convertedName);

        ImageFile imageFile = ImageFile.builder()
                .originalName(file.getOriginalFilename())
                .convertedName(convertedName)
                .s3Url(s3Url)
                .build();

        imageFileRepository.save(imageFile);

        return imageFile.toDto();
    }

    @Transactional
    public void deleteById(Long id) {
        ImageFile imageFile = imageFileRepository.findById(id)
                .orElseThrow(ImageFIleNotFoundException::new);

        deleteFromS3("skhufp/" + imageFile.getConvertedName());

        imageFileRepository.delete(imageFile);
    }

    private String uploadToS3(MultipartFile file, String newFileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(
                    new PutObjectRequest(bucket, newFileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw new CouldNotSaveFileInS3();
        }

        return amazonS3.getUrl(bucket, newFileName).toString();
    }

    private void deleteFromS3(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createRandomFileName(String originalFileName) {
        return UUID.randomUUID() + " " + originalFileName;
    }

    @PostConstruct
    private void setAmazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }
}
