package com.itability.memberservice.skill.dao;


import com.itability.memberservice.skill.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDAO extends JpaRepository<SkillEntity,Integer> {

}
