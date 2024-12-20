package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.model.Member;
import com.bootcamp.libraryProject.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<Member> getAllMembers(){
       return memberService.getAll();
    }

    @PostMapping("/members")
    public void createMember(@RequestBody Member newMember){
        memberService.addMember(newMember);
    }

    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable int id){
        memberService.deleteMember(id);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable int id){
        Optional<Member> foundMember = memberService.findMember(id);

        if(foundMember.isPresent()){
            return new ResponseEntity<>(foundMember.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member updateMember){
        try {
            Member member = memberService.updatedMember(id, updateMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
