package com.bluuminn.hellospring;

import com.bluuminn.hellospring.repository.MemberRepository;
import com.bluuminn.hellospring.repository.MemoryMemberRepository;
import com.bluuminn.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
