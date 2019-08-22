package com.neuqsoft.demo.service;


import com.neuqsoft.commons.spring.log.LogAnnotation;
import com.neuqsoft.commons.spring.message.GlobalMessage;
import com.neuqsoft.commons.util.DateUtils;
import com.neuqsoft.commons.util.UUIDUtils;
import com.neuqsoft.demo.constant.Constants;
import com.neuqsoft.demo.dto.UserDTO;
import com.neuqsoft.demo.entity.UserEntity;
import com.neuqsoft.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-05 13:38
 * @Description: 员工service层
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

   @LogAnnotation(value = "查询所有的人员",params = true,result = true)
   public List<UserEntity> findAllPerson(){
        return userRepo.findAll();
    }

    @LogAnnotation(value = "删除某个员工",params = true,result = true)
    public void deleteById(String id){
      userRepo.deleteById(id);
    }

    @LogAnnotation(value = "查询某个员工",params = true,result = true)
    public UserEntity findById(String id){
       UserEntity person=userRepo.findAllById(id);
       person.setName("张飞");
       userRepo.save(person);
       return person;
    }

    @LogAnnotation(value = "新增某个员工",params = true,result = true)
    public GlobalMessage saveUser(UserDTO userDTO){
       UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity.setId(UUIDUtils.uuid32());
        String nowTime= DateUtils.now("yyyyMMddHHmmss");
        userEntity.setBirthday(nowTime);
        userRepo.save(userEntity);
        return Constants.MessageRepo.SUCCESS;
    }

    @LogAnnotation(value = "修改某个员工",params = true,result = true)
    public GlobalMessage update(String id,UserDTO userDTO){
       UserEntity userEntity=userRepo.findAllById(id);
       if(userEntity!=null){
           BeanUtils.copyProperties(userDTO,userEntity);
       }
       userRepo.save(userEntity);
       return Constants.MessageRepo.SUCCESS;
    }

    @LogAnnotation(value = "批量添加信息")
    public List<UserEntity> saveAll(List<UserDTO> userDTOList){
       List<UserEntity> userEntityList=new ArrayList<>();
       for (UserDTO userDTO:userDTOList){
           UserEntity userEntity=new UserEntity();
           BeanUtils.copyProperties(userDTO,userEntity);
           userEntity.setId(UUIDUtils.uuid32());
           String tt=DateUtils.now("yyyyMMddHHmmss");
           userEntity.setBirthday(tt);
           userEntityList.add(userEntity);
       }
       userRepo.saveAll(userEntityList);
       return userEntityList;
    }

    @LogAnnotation(value = "分页查询",params = true,result = true)
    public Page<UserEntity> findBySex(String sex,int pageNo,int pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.Direction.DESC,"sex");
        return userRepo.findBySex(sex,pageable);
    }

    @LogAnnotation(value = "多条件动态查询",params = true,result = true)
    public Page<UserEntity> getUser(String name,String sex,String tel,int pageNo,int pageSize){
        return userRepo.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            if (!StringUtils.isEmpty(name)) {
                expressions.add(cb.like(root.<String>get("name"), "%" + name + "%"));
            }
            if (!StringUtils.isEmpty(sex)) {
                expressions.add(cb.equal(root.<String>get("sex"), sex));
            }
            if (!StringUtils.isEmpty(tel)) {
                expressions.add(cb.like(root.<String>get("tel"), "%" + tel + "%"));
            }
            return predicate;
        }, new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "name"));
    }
}
