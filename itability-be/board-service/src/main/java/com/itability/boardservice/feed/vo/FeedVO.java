package com.itability.boardservice.feed.vo;

import com.itability.boardservice.feed.dto.ImgDTO;
import com.itability.boardservice.reple.dto.CommentDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FeedVO {
        private int boardId;
        private String boardTitle;
        private String boardContent;
        private String writeDate;
        private int hits;
        private int reportCount;
        private int isActive;
        private long memberId;
        private ImgDTO imgId;
        private List<CommentDTO> comments;
}
