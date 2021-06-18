package com.chart.sungjun.controller;

import com.chart.sungjun.dto.BoardDto;
import com.chart.sungjun.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"/", ""})
    private String main(Model model, @RequestParam(value = "keyword",required = false) String keyword){
        log.info("keyword = " + keyword);

        List<BoardDto> boards = new ArrayList();
        // 키워드 조회
        boards = boardService.getBoardList();

        // 키워드 입력 시 구분 뒤 db저장
        if(keyword != null && keyword != "" ) {
            log.info("::::::::  keyword 전달 ::::::::::");
            boardService.saveKeyword(keyword);
        }

        model.addAttribute("boards", boards);
        return "index";
    }
}
