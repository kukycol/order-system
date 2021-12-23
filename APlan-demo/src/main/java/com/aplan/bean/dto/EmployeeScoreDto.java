package com.aplan.bean.dto;

import com.aplan.bean.model.ScoreModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeScoreDto {

    private String username;

    private String userId;

    private Integer score1;

    private Integer score2;

    private Integer score3;


}
