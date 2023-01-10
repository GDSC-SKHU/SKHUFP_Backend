package com.gdsc.skhufp.closet.domain.entity;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "image_url")
    private String imageUrl;
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

    public void update(ClothDTO dto) {
        this.imageUrl = dto.imageUrl();
        this.type = dto.type();
        this.seasons = dto.seasons();
        this.name = dto.name();
        this.comment = dto.comment();
    }

    public ClothDTO toDTO() {
        return ClothDTO.builder()
                .id(id)
                .imageUrl(imageUrl)
                .type(type)
                .seasons(seasons)
                .name(name)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }
}
