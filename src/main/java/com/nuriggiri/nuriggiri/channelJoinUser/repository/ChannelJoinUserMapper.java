package com.nuriggiri.nuriggiri.channelJoinUser.repository;

import com.nuriggiri.nuriggiri.channelJoinUser.domain.ChannelJoinUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelJoinUserMapper {

    // 채널 접속
    void joinCh(int userNo, int channelNo);

    // 채널 나가기
    void exitCh(int joinSeqNo);

}
