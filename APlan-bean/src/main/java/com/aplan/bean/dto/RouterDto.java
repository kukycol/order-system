package com.aplan.bean.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouterDto {

    private String path;
    private String component;
    private String name;
    private boolean hidden;
    private MetaDto meta;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String parentId;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private List<RouterDto> children;

}
