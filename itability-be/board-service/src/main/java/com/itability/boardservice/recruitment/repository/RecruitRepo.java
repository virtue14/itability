package com.itability.boardservice.recruitment.repository;

import com.itability.boardservice.recruitment.aggregate.RecruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepo extends JpaRepository<RecruitDTO, Integer> {

    List<RecruitDTO> findByMemberInfoDTO(long memberId);
}
