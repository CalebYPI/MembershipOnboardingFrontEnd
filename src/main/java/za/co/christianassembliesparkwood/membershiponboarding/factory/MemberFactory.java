package za.co.christianassembliesparkwood.membershiponboarding.factory;

import java.util.Date;

import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;
import za.co.christianassembliesparkwood.membershiponboarding.util.Helper;

public class MemberFactory {
    public static Member createMember(String firstName, String lastName, String gender, Date birthDate, String maritalStatus, String phoneNumber, String email, String address, String department, String status, String role) {
        String id = Helper.generateId();
        Member member = new Member.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setBirthDate(birthDate)
                .setMaritalStatus(maritalStatus)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setAddress(address)
                .setDepartment(department)
                .setStatus(status)
                .setRole(role)
                .build();
        return member;
    }
}
