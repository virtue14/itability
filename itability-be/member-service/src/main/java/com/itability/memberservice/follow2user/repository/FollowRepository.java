package com.itability.memberservice.follow2user.repository;

import com.itability.memberservice.follow2user.aggregate.Follow;
import com.itability.memberservice.follow2user.dto.FollowDTO;
import com.itability.memberservice.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFollowing(MemberInfoDTO following);
    List<Follow> findByFollowed(MemberInfoDTO followed);
}
