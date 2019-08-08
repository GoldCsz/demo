package com.neuqsoft.demo.service;


import com.neuqsoft.commons.util.DateUtils;
import com.neuqsoft.commons.util.UUIDUtils;
import com.neuqsoft.demo.dto.PersonDTO;
import com.neuqsoft.demo.entity.Person;
import com.neuqsoft.demo.repository.PersonRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-05 13:38
 * @Description: 员工service层
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;


   public List<Person> findAllPerson(){
        return personRepo.findAll();
    }

    public void deleteById(String id){
      personRepo.deleteById(id);
    }

    public Person findById(String id){
       Person person=personRepo.findAllById(id);
       person.setName("张飞");
       personRepo.save(person);
       return person;
    }

    public Optional<Person> test(String id){
       Optional<Person> optionalPerson=personRepo.findById(id);
       optionalPerson.get();
       return optionalPerson;
    }

    public Person Insertpeople(@RequestBody PersonDTO personDTO){
      Person person=new Person();
      //person.setId("555");
        person.setId(UUIDUtils.uuid32());
      person.setBirthday("20190807");
        BeanUtils.copyProperties(personDTO,person);
        personRepo.save(person);
        return person;
    }
    public Page<Person> findpersonpage(int pageNo, int pageSize){
       Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.Direction.DESC,"id");
       Page<Person> personPage=personRepo.findAll(pageable);
       String tt= DateUtils.now("yyyyMMddHHmmss");
        Date time=DateUtils.strToDate(tt,"yyyyMMddHHmmss");
       System.out.println(tt);
       System.out.println(time);
       return personPage;
    }

    //调用接口
   /* public Person testRes(String id){
        String url="http://localhost:8080/modify/id?id=";
        Person person=restTemplate.getForEntity(url+id,Person.class).getBody();
        return person;
    }*/


}
