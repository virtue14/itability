package com.itability.memberservice.mypage.dto;

import com.itability.memberservice.skill.entity.SkillEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainSkillDTO {
    private long memberId;
    private List<SkillEntity> memberSkillList;
    private List<SkillEntity> remainSkillList;
}
