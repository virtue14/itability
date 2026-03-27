package com.itability.boardservice.recruitment.repository;

import com.itability.boardservice.recruitment.vo.RecruitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitMapper {
    List<RecruitVO> findRecruitList();
}
