package com.shop.controller;

import com.shop.Service.MemberService;
import com.shop.dto.MemberFromDTO;
import com.shop.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFromDTO());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(MemberFromDTO memberFormDTO){
        Member member = Member.createMember(memberFormDTO, passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/";
    }
}
