package com.chart.sungjun.mapper;

import com.chart.sungjun.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 조회
    List<BoardDto> getBoardList();

    // 키워드저장
    int saveKeyword(String keyword);

    // 키워드 카운트
    int updateCount(String keyword);

    // 키워드 체크
    boolean keywordCheck(String keyword);
}
