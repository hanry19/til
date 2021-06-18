package com.chart.sungjun.service;

import com.chart.sungjun.dto.BoardDto;

import java.util.List;

public interface BoardService {

    // 조회
    List<BoardDto> getBoardList();

    // 키워드 구분
    List<BoardDto>  saveKeyword(String keyword);




}
