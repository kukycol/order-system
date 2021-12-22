package com.aplan.bean.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDto {

    private List<EmployeeScoreDto> scores;

    private String userId;

}
