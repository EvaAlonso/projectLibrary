package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Member;
import com.bootcamp.libraryProject.repository.IMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final IMemberRepository IMemberRepository;

    public MemberService(IMemberRepository IMemberRepository) {
        this.IMemberRepository = IMemberRepository;
    }
    public List<Member> getAll(){
        return IMemberRepository.findAll();
    }
    public Member addMember(Member newMember){
        return IMemberRepository.save(newMember);
    }
    public void deleteMember(int id){
        IMemberRepository.deleteById(id);
    }
    public Optional<Member> findMember(int id){
        return IMemberRepository.findById(id);
    }
    public Member updatedMember(int id, Member updateMember){
        Optional<Member> foundMember = IMemberRepository.findById(id);

        if(foundMember.isPresent()){
            Member existingMember = foundMember.get();
            existingMember.setName(updateMember.getName());
            existingMember.setSurname(updateMember.getSurname());
            existingMember.setDirection(updateMember.getDirection());
            existingMember.setPhone(updateMember.getPhone());
            existingMember.setEmail(updateMember.getEmail());

            return IMemberRepository.save(existingMember);
        }
        throw new RuntimeException("Member not found with id: " + id);
    }
}
