package com.example.demo.dao;

import com.example.demo.dto.SampleUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SampleUserMapper {

    SampleUserDto findByUsername(@Param("username") String username);

    SampleUserDto findByUserId(@Param("id") int id);

}
