package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.exception.ObjectNotFoundException;
import com.bootcamp.libraryProject.model.Member;
import com.bootcamp.libraryProject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository MemberRepository) {
        this.memberRepository = MemberRepository;
    }
    public List<Member> getAll(){
        return memberRepository.findAll();
    }
    public Member addMember(Member newMember){
        return memberRepository.save(newMember);
    }
    public void deleteMember(int id){
        memberRepository.deleteById(id);
    }
    public Optional<Member> findMember(int id){
        Optional<Member> foundMember = memberRepository.findById(id);
        if (foundMember.isPresent()){
            return memberRepository.findById(id);
        }
        throw new ObjectNotFoundException("Member", id);
    }
    public Member updatedMember(int id, Member updateMember){
        Optional<Member> foundMember = memberRepository.findById(id);

        if(foundMember.isPresent()){
            Member existingMember = foundMember.get();
            existingMember.setName(updateMember.getName());
            existingMember.setSurname(updateMember.getSurname());
            existingMember.setDirection(updateMember.getDirection());
            existingMember.setPhone(updateMember.getPhone());
            existingMember.setEmail(updateMember.getEmail());

            return memberRepository.save(existingMember);
        }
        throw new RuntimeException("Member not found with id: " + id);
    }
}
