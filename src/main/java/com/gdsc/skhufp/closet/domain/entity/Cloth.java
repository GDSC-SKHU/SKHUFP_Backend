package com.gdsc.skhufp.closet.domain.entity;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String image_url;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "comment")
    private String comment;
    @Column(name = "name")
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
