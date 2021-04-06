package com.example.demo.dao;

import com.example.demo.dto.SampleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SampleMapper {

    List<SampleDto> listSample(@Param("name") String name);

    void registSample(@Param("dto") SampleDto dto);

}
