package com.chart.sungjun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@Alias("boardDTO")
public class BoardDto {

    private Long id;
    private String keyword;
    private Long count;
    private String regDate;

}
