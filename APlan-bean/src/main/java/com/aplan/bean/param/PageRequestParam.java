
package com.aplan.bean.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "分页请求参数")
public class PageRequestParam{

    @ApiModelProperty(value = "条数",example = "10")
    private long size = 10;

    @ApiModelProperty(value = "页数",example = "1")
    private long current = 1;

}
