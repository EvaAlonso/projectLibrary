package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Member;
import com.bootcamp.libraryProject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository MemberRepository;

    public MemberService(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }
    public List<Member> getAll(){
        return MemberRepository.findAll();
    }
    public Member addMember(Member newMember){
        return MemberRepository.save(newMember);
    }
    public void deleteMember(int id){
        MemberRepository.deleteById(id);
    }
    public Optional<Member> findMember(int id){
        return MemberRepository.findById(id);
    }
    public Member updatedMember(int id, Member updateMember){
        Optional<Member> foundMember = MemberRepository.findById(id);

        if(foundMember.isPresent()){
            Member existingMember = foundMember.get();
            existingMember.setName(updateMember.getName());
            existingMember.setSurname(updateMember.getSurname());
            existingMember.setDirection(updateMember.getDirection());
            existingMember.setPhone(updateMember.getPhone());
            existingMember.setEmail(updateMember.getEmail());

            return MemberRepository.save(existingMember);
        }
        throw new RuntimeException("Member not found with id: " + id);
    }
}
