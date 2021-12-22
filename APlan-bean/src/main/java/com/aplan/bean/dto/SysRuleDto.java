package com.aplan.bean.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 权限表单，上级列表
 */
@Data
public class SysRuleDto implements Serializable {
    private static final long serialVersionUID = -63773539610072643L;

    private String id;

    private String label;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String parentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer ruleLevel;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean disabled = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<SysRuleDto> children;
}