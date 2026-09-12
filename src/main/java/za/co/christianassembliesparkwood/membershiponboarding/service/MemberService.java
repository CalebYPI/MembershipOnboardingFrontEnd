package za.co.christianassembliesparkwood.membershiponboarding.service;

import java.util.Set;
import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;

public interface MemberService extends IService<Member, String> {
    Set<Member> getAll();
}
