package com.itability.memberservice.follow2user.dto;


import com.itability.memberservice.member.dto.MemberInfoDTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FollowDTO {
    private int followId;

    private MemberInfoDTO following;

    private MemberInfoDTO followed;
    public String getfollowing_nickname() {
        return following != null ? following.getName() : null;
    }

    public String getfollowed_nickname() {
        return followed != null ? followed.getName() : null;
    }

}
