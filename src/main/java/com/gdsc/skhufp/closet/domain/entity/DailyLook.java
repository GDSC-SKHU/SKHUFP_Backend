package com.gdsc.skhufp.closet.domain.entity;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.request.DailyLookRequest;
import com.gdsc.skhufp.closet.dto.response.ClothResponse;
import com.gdsc.skhufp.closet.dto.response.DailyLookResponse;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gdsc.skhufp.closet.domain.entity.ClothType.*;

@Entity
@Table(name = "daliy_look")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DailyLook extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "comment")
    private String comment;
    @ManyToMany(targetEntity = Cloth.class, fetch = FetchType.EAGER)
    @JoinTable(name = "daily_look_cloth")
    @Builder.Default
    private List<Cloth> cloths = new ArrayList<>();
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(DailyLookRequest request, List<Cloth> cloths) {
        this.name = request.name();
        this.comment = request.comment();
        this.cloths = cloths;
    }

    public DailyLookResponse toResponseDTO() {
        List<ClothResponse> etcList = new ArrayList<>();
        Map<ClothType, ClothResponse> map = new HashMap<>();

        for (Cloth cloth : cloths) {
            ClothType type = cloth.getType();
            ClothResponse response = cloth.toResponseDTO();

            if (type == ETC) {
                etcList.add(response);
            } else {
                map.put(cloth.getType(), cloth.toResponseDTO());
            }
        }

        return DailyLookResponse.builder()
                .id(id)
                .name(name)
                .comment(comment)
                .tops(map.getOrDefault(TOPS, null))
                .bottoms(map.getOrDefault(BOTTOMS, null))
                .outerwear(map.getOrDefault(OUTERWEAR, null))
                .shoes(map.getOrDefault(SHOES, null))
                .bags(map.getOrDefault(BAGS, null))
                .etc(etcList)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }
}
