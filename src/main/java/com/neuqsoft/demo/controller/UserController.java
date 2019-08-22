package com.neuqsoft.demo.controller;

import com.neuqsoft.commons.spring.message.GlobalMessage;
import com.neuqsoft.demo.dto.UserDTO;
import com.neuqsoft.demo.entity.UserEntity;
import com.neuqsoft.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-08 11:40
 * @Description: 控制层
 */
@RestController
@RequestMapping("/person")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有员工信息")
    @GetMapping("/findAll")
    public List<UserEntity> getAllPerson(){
        List<UserEntity> person=userService.findAllPerson();
        return person;
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/delete/{id}")
    public void deleteByid(@RequestParam String id){
        userService.deleteById(id);
    }

    @ApiOperation("查询员工信息")
    @PostMapping("find/{id}")
    public UserEntity modifyByid(@RequestParam  String id){
        return userService.findById(id);
    }

    @ApiOperation("单个新增员工")
    @PostMapping("/add")
    public GlobalMessage addUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @ApiOperation("修改某个员工信息")
    @PutMapping("/update")
    public GlobalMessage updateUser(@RequestParam String id,@RequestBody UserDTO userDTO){
        return userService.update(id, userDTO);
    }

    @ApiOperation("批量新增信息")
    @PutMapping("/addAll")
    public List<UserEntity> addAll(@RequestBody List<UserDTO> userDTOList){
        List<UserEntity> userEntityList=userService.saveAll(userDTOList);
        return  userEntityList;
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Page<UserEntity> findBysex(@RequestParam String sex,@RequestParam int pageNo,@RequestParam int pageSize){
        return userService.findBySex(sex, pageNo, pageSize);
    }

    @ApiOperation("多条件动态查询")
    @GetMapping("/test")
    public Page<UserEntity> getUser(@RequestParam(required = false)  String name,
                                    @RequestParam(required = false)  String sex,
                                    @RequestParam (required = false) String tel,
                                    @RequestParam(required = false,defaultValue = "0") int pageNo,
                                    @RequestParam(required = false,defaultValue = "5") int pageSize){
        return userService.getUser(name, sex, tel, pageNo, pageSize);
    }




}
