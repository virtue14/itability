package com.itability.memberservice.mypage.dao;


import com.itability.memberservice.mypage.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileDAO extends JpaRepository<MemberProfileEntity,Long> {

}
