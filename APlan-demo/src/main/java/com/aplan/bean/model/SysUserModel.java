package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.aplan.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@TableName("sys_user")
@ApiModel(value = "sys_user")
public class SysUserModel implements Serializable, UserDetails {
    private static final long serialVersionUID = 319299281177082310L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String userId;

    @TableField(value = "`alias`")
    @ApiModelProperty(value = "别名")
    private String alias;

    @TableField(value = "`username`")
    @ApiModelProperty(value = "账号")
    private String username;

    @TableField(value = "`depart_id`")
    @ApiModelProperty(value = "部门主键")
    private String departId;

    //只允许写入，不允许读取
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "`enabled`")
    @ApiModelProperty(value = "启用，启用0，禁用1")
    private Integer enabled;

    @TableField(value = "`online`")
    @ApiModelProperty(value = "在线状态，在线0，下线1")
    private Integer online;

    @TableField(value = "`mobile`")
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @TableField(value = "`avatar`")
    @ApiModelProperty(value = "头像")
    @JsonSerialize(using = ImgJsonSerializer.class)
    private String avatar;

    @TableField(value = "`introduction`")
    @ApiModelProperty(value = "个人介绍")
    private String introduction;

    @TableField(value = "`sex`")
    @ApiModelProperty(value = "性别，女0，男1")
    private Integer sex;

    @TableField(exist = false)
    private List<String> roles;

    @TableField(exist = false)
    private List<String> rules;

//    private String menus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled == 0 ? true : false;
    }
}