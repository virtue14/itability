package com.itability.memberservice.skill.entity;

//import com.itability.mypage.MemberProfileDTO;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "member_skill_dto")
@Table(name = "member_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkillEntity {
    @EmbeddedId
    private MemberSkillId id;
}
