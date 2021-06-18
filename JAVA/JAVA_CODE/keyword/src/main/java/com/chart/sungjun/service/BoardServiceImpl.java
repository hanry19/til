package com.chart.sungjun.service;

import com.chart.sungjun.dto.BoardDto;
import com.chart.sungjun.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDto> getBoardList() {
        log.info(":::::::::::::: 게시판 조회  ::::::::::::::::");
        return boardMapper.getBoardList();
    }

    @Override
    public List<BoardDto> saveKeyword(String keyword) {
        String[] keywords = keyword.split(" ");



        // 키워드 중복 제거
        ArrayList<String> words = new ArrayList<>();
        for (String word : keywords) {
            if(!words.contains(word))
                words.add(word);
        }

        // 키워드 저장 및 카운트 증가
        for (String s : words) {
            log.info("keywords in service = " + s);

            if(boardMapper.keywordCheck(s)==true){
                log.info("존재하는 키워드 [count +1] = " + s);
                boardMapper.updateCount(s);
            }else if(boardMapper.keywordCheck(s)== false){
                log.info("새로운 키워드 등록 = " + s);
                boardMapper.saveKeyword(s);
            }
        }
        return boardMapper.getBoardList();
    }

}
