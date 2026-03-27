package com.itability.memberservice.skill.dto;

import com.itability.memberservice.skill.entity.MemberSkillId;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseSkillList {
    List<MemberSkillId> skillList;
}
