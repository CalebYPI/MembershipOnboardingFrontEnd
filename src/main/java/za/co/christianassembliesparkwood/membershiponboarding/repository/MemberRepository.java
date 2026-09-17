package za.co.christianassembliesparkwood.membershiponboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
