package com.bluuminn.hellospring.service;

import com.bluuminn.hellospring.domain.Member;
import com.bluuminn.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}