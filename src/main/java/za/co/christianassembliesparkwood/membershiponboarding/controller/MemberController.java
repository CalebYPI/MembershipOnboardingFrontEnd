package za.co.christianassembliesparkwood.membershiponboarding.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import za.co.christianassembliesparkwood.membershiponboarding.entity.Member;
import za.co.christianassembliesparkwood.membershiponboarding.factory.MemberFactory;
import za.co.christianassembliesparkwood.membershiponboarding.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("members", service.getAll());
        return "memberHome";
    }

    @GetMapping("/create")
    public String getCreateForm(Member member) {
        return "memberAdd";
    }

    @PostMapping(value ="/create")
    public String create(@ModelAttribute Member member, BindingResult result, Model model) {
        if (result.hasErrors())
            return "memberError";
        Member newMember = MemberFactory.createMember(member.getFirstName(), member.getLastName(), member.getGender(), member.getBirthDate(), member.getMaritalStatus(), member.getPhoneNumber(), member.getEmail(), member.getAddress(), member.getDepartment(), member.getStatus(), member.getRole());
        service.create(newMember);
        return "redirect:/member/home";
    }

    @GetMapping("/read/{id}")
    public Member read(@PathVariable String id) {
        return service.read(id);
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") String id, Model model) {
        Member member = service.read(id);
        model.addAttribute("member", member);
        return "memberUpdate";
    }

    @PostMapping("/update")
    public String update(Member member, BindingResult result, Model model) {
        if (result.hasErrors())
            return "memberUpdate";
        service.update(member);
        return "redirect:/member/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        service.delete(id);
        model.addAttribute("member", service.getAll());
        return "redirect:/member/home";
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return service.delete(id);
    }

    @GetMapping(value = "/all")
    public Set<Member> getAll() {
        return service.getAll();
    }
}
