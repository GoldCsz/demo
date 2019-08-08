package com.neuqsoft.demo.repository;

import com.neuqsoft.demo.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: chensz
 * @CreateTime: 2019-08-05 13:35
 * @Description: 员工接口
 */
@Repository
public interface PersonRepo extends JpaRepository<Person,String> {


    List<Person> findAll();

    void deleteById(String ID);

    Person findAllById(String id);

    Optional<Person> findById(String id);

    Page<Person> findAll(Pageable pageable);
}
