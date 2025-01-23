package com.shop.Service;

import com.shop.dto.MemberFromDTO;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberservice;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){

        MemberFromDTO memberFromDTO = new MemberFromDTO();

        memberFromDTO.setEmail("test@email.com");
        memberFromDTO.setName("홍길동");
        memberFromDTO.setAddress("서울");
        memberFromDTO.setPassword("1234");
        return Member.createMember(memberFromDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember =memberservice.saveMember(member);

        assertEquals(member.getEmail(),savedMember.getEmail());
        assertEquals(member.getName(),savedMember.getName());
        assertEquals(member.getAddress(),savedMember.getAddress());
        assertEquals(member.getPassword(),savedMember.getPassword());
        assertEquals(member.getRole(),savedMember.getRole());

    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveDuplicateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberservice.saveMember(member1);

       Throwable e = assertThrows(IllegalStateException.class,()->{
               memberservice.saveMember(member2);});

       assertEquals("이미 가입됨",e.getMessage());
}
}