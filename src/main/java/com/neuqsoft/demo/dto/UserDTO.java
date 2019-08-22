package com.neuqsoft.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-07 13:27
 * @Description: 常用的属性值
 */
@Data
@ApiModel(value = "UserDTO",description = "员工的基本信息dto")
public class UserDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("地址")
    private String address;

}
