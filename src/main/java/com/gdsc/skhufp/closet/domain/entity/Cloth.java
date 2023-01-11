package com.gdsc.skhufp.closet.domain.entity;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.request.ClothRequest;
import com.gdsc.skhufp.closet.dto.response.ClothResponse;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;
import com.gdsc.skhufp.storage.domain.model.ImageFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "cloth")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cloth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(targetEntity = ImageFile.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageFile image;
    @Column(name = "type", nullable = false)
    private ClothType type;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @BatchSize(size = 1)
    private Set<Season> seasons = new HashSet<>();
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(ClothRequest dto) {
        this.type = dto.type();
        this.seasons = dto.seasons();
        this.name = dto.name();
        this.comment = dto.comment();
    }

    public ClothResponse toResponseDTO() {
        ImageFile imageFile = image;
        String imageUrl = (imageFile != null) ? image.getS3Url() : "";

        return ClothResponse.builder()
                .id(id)
                .imageUrl(imageUrl)
                .type(type.name().toLowerCase())
                .seasons(
                        seasons.stream()
                                .map(x -> x.name().toLowerCase())
                                .collect(Collectors.toList())
                )
                .name(name)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }

    public void setImage(ImageFile image) {
        this.image = image;
    }
}
