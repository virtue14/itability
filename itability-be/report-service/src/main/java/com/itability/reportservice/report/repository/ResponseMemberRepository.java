package com.itability.reportservice.report.repository;

import com.itability.reportservice.report.aggregate.Member;
import com.itability.reportservice.vo.ResponseMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMbReportCountGreaterThanEqual(int i);
}
