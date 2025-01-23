package com.shop.entity;

import com.shop.config.Role;
import com.shop.dto.MemberFromDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
@Builder
public class Member {


    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String name;

    @Column(unique = true)

    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFromDTO memberFromDTO, PasswordEncoder passwordEncoder) {

        Member member = Member.builder()
                .role(Role.USER)
                .email(memberFromDTO.getEmail())
                .address(memberFromDTO.getAddress())
                .name(memberFromDTO.getName())
                .password(passwordEncoder.encode(memberFromDTO.getPassword()))
                .build();

        String password = memberFromDTO.getPassword();
        return member;
    }
}

