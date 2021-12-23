package com.aplan.common;

import com.aplan.bean.dto.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.aplan.bean.model.SysRuleModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lgsh
 * @Description:
 * @date 2021/10/28 17:08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Routers implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String redirect;
    private String component;
    private String path;
    private String parentId;
    private List<SysRuleModel> children;
    private Meta meta;
}
