package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepository extends JpaRepository<Member, Integer> {
}
