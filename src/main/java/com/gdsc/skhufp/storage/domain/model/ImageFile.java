package com.gdsc.skhufp.storage.domain.model;

import com.gdsc.skhufp.common.entity.BaseTimeEntity;
import com.gdsc.skhufp.storage.dto.ImageFileDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "image_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ImageFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "original_name", nullable = false, length = 500)
    private String originalName;

    @Column(name = "converted_name", nullable = false, length = 500)
    private String convertedName;

    @Column(name = "s3_url", nullable = false, length = 1000)
    private String s3Url;

    public ImageFileDTO toDto() {
        return ImageFileDTO.builder()
                .id(id)
                .originalName(originalName)
                .convertedName(convertedName)
                .s3Url(s3Url)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }
}