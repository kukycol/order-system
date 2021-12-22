package com.aplan.bean.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author lgsh
 * @Description:
 * @date 2021/10/28 19:29
 */
@Data
@Builder
public class Meta {
    private String title;
    private String icon;

    @Tolerate
    public Meta() {
    }
}
