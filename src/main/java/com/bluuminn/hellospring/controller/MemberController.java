package com.bluuminn.hellospring.controller;

import com.bluuminn.hellospring.domain.Member;
import com.bluuminn.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
//        System.out.println("service = " + service.getClass());  -> AOP 적용 후 확인해보면 프록시 객체인 것을 알 수 있다.
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        service.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = service.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}