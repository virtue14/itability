package com.itability.memberservice.member.dao;

import com.itability.memberservice.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoRepo extends JpaRepository<MemberInfoDTO,Long> {
    List<MemberInfoDTO> findByMbReportCountGreaterThanEqual(int mbReportCount);

    MemberInfoDTO findByUsername(String username);
}
