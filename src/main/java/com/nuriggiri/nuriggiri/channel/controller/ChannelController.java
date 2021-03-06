package com.nuriggiri.nuriggiri.channel.controller;

import com.nuriggiri.nuriggiri.board.paging.Criteria;
import com.nuriggiri.nuriggiri.board.paging.PageMaker;
import com.nuriggiri.nuriggiri.channel.domain.Channel;
import com.nuriggiri.nuriggiri.channel.domain.ModifyChannel;
import com.nuriggiri.nuriggiri.channel.service.ChannelService;
import com.nuriggiri.nuriggiri.channelJoinUser.service.ChannelJoinUserService;
import com.nuriggiri.nuriggiri.user.domain.User;
import com.nuriggiri.nuriggiri.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Log4j2
//@CrossOrigin
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

    //서비스 파일과 연결
    public final ChannelService channelService;

    public final ChannelJoinUserService channelJoinUserService;

    public final UserService userService;



    //채널 목록 가져오기
    @GetMapping(value = {"/chList", "/chMain"})
    public String viewList(Criteria criteria, Model model, HttpSession session) {
        List<Channel> channelList = channelService.viewList();
        model.addAttribute("list", channelList);
        model.addAttribute("pageMaker",
        new PageMaker(criteria, channelService.getTotal(criteria)));
        session.setAttribute("chList", channelList);
        return "/channel/chMain";
    }

    //검색된 채널 목록 가져오기
    @GetMapping("/searchCh")
    public String searchList(Criteria criteria, Model model) {
        model.addAttribute("searchList", channelService.viewList(criteria));
        return "/channel/searchCh";
    }

    //채널 생성 화면 요청
    @GetMapping("/addCh")
    public String create(Model model) {
        List<Channel> channelList = channelService.viewList();
        model.addAttribute("list", channelList);
        return "/channel/addCh";
    }

    //채널 생성 처리 요청
    @PostMapping("/addCh")
    public String create(Channel channel) {
        try {
            channelService.create(channel);
        } catch (Exception e) {
            return "/channel/addCh";
        }
        return "redirect:/channel/chList";
    }

    //채널 정보 상세보기 요청
    @GetMapping("/viewCh/{channelNo}")
    public String viewInfo(@PathVariable int channelNo, Model model) {
        Channel content = channelService.viewInfo(channelNo);
        model.addAttribute("channel", content);
        User user = userService.userInfoNo(content.getAdminUserNo());
        List<Channel> channelList = channelService.viewList();
        model.addAttribute("list", channelList);
        model.addAttribute("userInfo",user);
        return "/channel/viewCh";
    }

    //채널 수정 화면 요청
    @GetMapping("/modCh")
    public String update(int channelNo, Model model) {
        model.addAttribute("channel", channelService.viewInfo(channelNo));
        List<Channel> channelList = channelService.viewList();
        model.addAttribute("list", channelList);
        return "/channel/modCh";
    }

    //채널 수정 처리 요청
    @PostMapping("/modCh")
    public String update(ModifyChannel modifyChannel) {
        // 원본데이터를 찾아서 수정데이터로 변경하는 로직
        Channel channel = channelService.viewInfo(modifyChannel.getChannelNo());
        channel.setChannelName(modifyChannel.getChannelName());
        channel.setChannelInfo(modifyChannel.getChannelInfo());
        channel.setChannelPw(modifyChannel.getChannelPw());
        try {
            channelService.update(channel);
        } catch (Exception e) {
            return "redirect:/channel/modCh?channelNo=" + modifyChannel.getChannelNo();
        }
        return "redirect:/channel/viewCh/" + modifyChannel.getChannelNo();
    }

    //채널 삭제 요청
    @GetMapping("/delete")
    public String delete(int channelNo) {
        channelService.delete(channelNo);
        return "redirect:/channel/chMain";
    }














}
