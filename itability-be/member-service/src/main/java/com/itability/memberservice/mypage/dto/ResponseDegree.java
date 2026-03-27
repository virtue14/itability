package com.itability.memberservice.mypage.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDegree {
    MemberProfileDTO profile;
    DegreeDTO degree;
}
