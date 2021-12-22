package com.aplan.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.aplan.annotation.BeanSetter;
import com.aplan.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@TableName("sys_user")
public class SysUserDto implements Serializable {
    private static final long serialVersionUID = 319299281177082310L;

    /**
     * 用户
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String userId;

    /**
     * 别名
     */
    @NotBlank(message = "别名不允许为空哦")
    @ApiModelProperty(value = "别名")
    private String alias;

    /**
     * 部门主键
     */
    @NotBlank(message = "请选中一个部门")
    @ApiModelProperty(value = "部门主键")
    private String departId;

    /**
     * 账号
     */
    @NotBlank(message = "账号不允许为空哦")
    @Pattern(regexp = "^[A-Za-z0-9]{3,50}$",message = "格式支持：英文、数字，3-50个字符；账号不符合要求，请检查")
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 状态
     */
    @NotNull(message = "状态不允许为空哦")
    @Range(min = 0,max = 1,message = "格式支持：0、1；状态不符合要求，请检查")
    @ApiModelProperty(value = "状态，启用0，禁用1")
    private Integer enabled;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不允许为空哦")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",message = "格式支持：数字，只11个字符；手机号码不符合要求，请检查")
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 头像
     */
    @NotBlank(message = "头像不允许为空哦")
    @ApiModelProperty(value = "头像")
    @JsonSerialize(using = ImgJsonSerializer.class)
    @BeanSetter
    private String avatar;


    @TableField(exist = false)
    @ApiModelProperty(value = "部门名称")
    private String departName;


    @TableField(exist = false)
    @ApiModelProperty(value = "职位名称")
    private String positionName;

    /**
     * 性别
     */
    @NotNull(message = "性别不允许为空哦")
    @Range(min = 0,max = 1,message = "格式支持：0、1；性别不符合要求，请检查")
    @ApiModelProperty(value = "性别，女0，男1")
    private Integer sex;

    /**
     * 多角色
     */
    @NotNull(message = "请选择角色")
    @Size(min = 1,message = "请选择角色")
    @TableField(exist = false)
    private List<String> roles;

    /**
     * 多职位
     */
//    @NotNull(message = "请选择职位")
//    @Size(min = 1,message = "请选择职位")
//    @TableField(exist = false)
    @ApiModelProperty(value = "职位主键")
    private String positionId;

    /**
     * 个人介绍
     */
    @ApiModelProperty(value = "个人介绍")
    private String introduction;

//
//    public void setAvatar(String avatar) {
//        String baseUrl = "http://192.168.0.46:8986/";
//        if (avatar.contains(baseUrl)){
//            avatar = avatar.substring(baseUrl.length(),avatar.length());
//        }
//        this.avatar = avatar;
//    }
}