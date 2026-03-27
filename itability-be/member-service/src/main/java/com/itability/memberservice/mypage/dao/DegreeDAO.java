package com.itability.memberservice.mypage.dao;

import com.itability.memberservice.mypage.entity.DegreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DegreeDAO extends JpaRepository<DegreeEntity,Integer> {

}
