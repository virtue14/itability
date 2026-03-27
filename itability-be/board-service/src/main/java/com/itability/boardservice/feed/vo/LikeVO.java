package com.itability.boardservice.feed.vo;
import com.itability.boardservice.feed.dto.FeedDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LikeVO {
    private FeedDTO boardId;
    private long memberId;
}

