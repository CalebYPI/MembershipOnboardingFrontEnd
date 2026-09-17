package za.co.christianassembliesparkwood.membershiponboarding.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;
import za.co.christianassembliesparkwood.membershiponboarding.repository.MemberRepository;
import za.co.christianassembliesparkwood.membershiponboarding.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member create(Member member) {
        return this.repository.save(member);
    }

    @Override
    public Member read(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Member update(Member member) {
        if (this.repository.existsById(member.getId()))
            return this.repository.save(member);
        return null;
    }

    @Override
    public boolean delete(String id) {
        this.repository.deleteById(id);
        return !this.repository.existsById(id);
    }

    @Override
    public Set<Member> getAll() {
        return new HashSet<>(this.repository.findAll());
    }
}
