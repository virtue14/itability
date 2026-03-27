package com.itability.boardservice.mypage.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity(name = "member_recruit_category_dto")
@Table(name = "member_recruit_category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRecruitCategoryEntity {
    @EmbeddedId
    private MemberRecruitCategoryId Id;

}
