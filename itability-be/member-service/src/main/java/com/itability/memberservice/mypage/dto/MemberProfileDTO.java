package com.itability.memberservice.mypage.dto;


import com.itability.memberservice.img.entity.ImageEntity;
import com.itability.memberservice.member.dto.MemberInfoDTO;
import com.itability.memberservice.mypage.entity.DegreeEntity;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MemberProfileDTO {
    private String nickname;
    private MemberInfoDTO memberInfo;
    private ImageEntity img;
    private DegreeEntity degree;


    public MemberProfileDTO(MemberInfoDTO memberInfo, String nickname, ImageEntity img) {
        this.memberInfo = memberInfo;
        this.nickname = nickname;
        this.img = img;
    }
}
