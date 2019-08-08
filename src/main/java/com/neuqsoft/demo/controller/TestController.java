package com.neuqsoft.demo.controller;

import com.neuqsoft.demo.dto.PersonDTO;
import com.neuqsoft.demo.entity.Person;
import com.neuqsoft.demo.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-08 11:40
 * @Description: 控制层
 */
@RestController
@RequestMapping("/person")
public class TestController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "获取所有员工信息")
    @GetMapping("/find")
    public List<Person> getAllPerson(){
        List<Person> person=personService.findAllPerson();
        return person;
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/delete/{id}")
    public void deleteByid( String id){
        personService.deleteById(id);
    }

    @ApiOperation("修改员工信息")
    @PostMapping("modify/{id}")
    public Person modifyByid(String id){
        return personService.findById(id);
    }

    @ApiOperation("测试一下")
    @PostMapping("/test")
    public Optional<Person> test1(String id){
        return  personService.test(id);
    }

    @ApiOperation("测试一下2")
    @PostMapping("/test2")
    public Person  test2(@RequestBody PersonDTO personDTO){
        return personService.Insertpeople(personDTO);
    }

    @ApiOperation("测试一下3")
    @PostMapping ("/test3")
    public Page<Person> findpa(@RequestParam int pageNo, @RequestParam int pageSize){
        return personService.findpersonpage(pageNo,pageSize);
    }
}
