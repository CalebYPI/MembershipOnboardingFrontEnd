package za.co.christianassembliesparkwood.membershiponboarding.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
}
