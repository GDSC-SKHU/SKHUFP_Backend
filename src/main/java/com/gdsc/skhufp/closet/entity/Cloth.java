package com.gdsc.skhufp.closet.entity;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "image_url", nullable = true)
    private String image_url;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "comment", nullable = true)
    private String comment;
    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(ClothDTO dto) {
        this.comment = dto.getComment();
        this.image_url = dto.getImage_url();
        this.type = dto.getType();
        this.name = dto.getName();
    }
    public ClothDTO toDTO() {
        return ClothDTO.builder()
                .id(id)
                .name(name)
                .image_url(image_url)
                .type(type)
                .comment(comment)
                .created_date(createdDate)
                .modified_date(modifiedDate)
                .build();
    }
}
