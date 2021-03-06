package com.nuriggiri.nuriggiri.reply.repository;

import com.nuriggiri.nuriggiri.board.paging.Criteria;
import com.nuriggiri.nuriggiri.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력
    int save(Reply reply);

    //댓글 수정
    int update(Reply reply);

    //댓글 삭제
    int delete(int replyNo);

    //댓글 1개 조회
    Reply read(int replyNo);

    //특정 게시물 댓글 목록 조회
    List<Reply> getList(@Param("bno") int boardNo
                        , @Param("cri") Criteria criteria);

    //특정 게시글의 댓글 총 개수 조회
    int getCount(int boardNo);
}
