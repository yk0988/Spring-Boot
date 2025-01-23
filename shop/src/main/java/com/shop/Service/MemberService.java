package com.shop.Service;

import com.shop.entity.Member;
import com.shop.repository.Memberepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final Memberepository memberepository;

    public Member saveMember(Member member){

        validateDuplicateMember(member);
        return memberepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> findMember = memberepository.findByEmail(member.getEmail());

        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입됨");

        }
    }

}
