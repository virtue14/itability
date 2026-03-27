package com.itability.boardservice.recruitment.repository;

import com.itability.boardservice.recruitment.aggregate.RecruitSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitSkillRepo extends JpaRepository<RecruitSkillDTO, Integer> {

    List<RecruitSkillDTO> findAllByIdRecruitId(int recruitId);
}
