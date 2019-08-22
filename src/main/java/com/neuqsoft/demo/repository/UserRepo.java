package com.neuqsoft.demo.repository;

import com.neuqsoft.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-05 13:35
 * @Description: 员工接口
 */
@Repository
public interface UserRepo extends JpaRepository<UserEntity,String> {


    List<UserEntity> findAll();

    void deleteById(String ID);

    UserEntity findAllById(String id);

    Page<UserEntity> findBySex(String sex, Pageable pageable);

    //多条件动态查询
    Page<UserEntity> findAll(Specification<UserEntity> spec,Pageable pageable);

}
