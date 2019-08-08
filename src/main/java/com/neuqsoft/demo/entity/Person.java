package com.neuqsoft.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-01 18:48
 * @Description: 实体类
 */
@Entity
@Data
@Table(name = "person")
@ApiModel(description = "员工信息",value = "Person")
public class Person {

    @Id
    @ApiModelProperty("员工号")
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
