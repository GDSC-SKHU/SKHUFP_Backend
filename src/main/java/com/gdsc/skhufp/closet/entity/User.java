package com.gdsc.skhufp.closet.entity;


import com.gdsc.skhufp.closet.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import com.gdsc.skhufp.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Cloth> clothes = new ArrayList<>();

    /*
        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;

        @OneToMany(mappedBy = "cloth", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
        private List<user> clothes = new ArrayList<>();*/
    public void update(UserDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }
    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .username(username)
                .password(password)
                .created_date(createdDate)
                .modified_date(modifiedDate)
                .build();
    }
}
