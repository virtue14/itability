package com.itability.boardservice.mypage.dao;


import com.itability.boardservice.mypage.entity.MemberRecruitCategoryEntity;
import com.itability.boardservice.mypage.entity.MemberRecruitCategoryId;
import com.itability.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRecruitCategoryDAO extends JpaRepository<MemberRecruitCategoryEntity, MemberRecruitCategoryId> {
    void deleteAllByIdMemberId(long memberId);

    List<MemberRecruitCategoryEntity> findByIdMemberId(Long memberId);
}
