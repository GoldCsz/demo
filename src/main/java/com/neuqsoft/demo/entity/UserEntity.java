package com.neuqsoft.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-01 18:48
 * @Description: 实体类
 */
@Entity
@Data
@Table(name = "user")
@ApiModel(description = "个人信息",value = "UserEntity")
public class UserEntity implements Serializable {

    @Id
    @ApiModelProperty("id")
    private  String id;

    @ApiModelProperty("员工姓名")
    private  String  name;

    @ApiModelProperty("员工性别")
    private String sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("生日")
    private String birthday;








}
