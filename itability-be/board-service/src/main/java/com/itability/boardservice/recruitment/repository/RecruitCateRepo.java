package com.itability.boardservice.recruitment.repository;

import com.itability.boardservice.recruitment.aggregate.RecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitCateRepo extends JpaRepository<RecruitCategoryDTO, Integer> {
}
