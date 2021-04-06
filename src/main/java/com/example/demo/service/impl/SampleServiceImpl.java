package com.example.demo.service.impl;

import com.example.demo.dao.SampleMapper;
import com.example.demo.dto.SampleDto;
import com.example.demo.service.SampleService;
import com.example.demo.service.annotation.ReadTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public String sampleService() {
        return "test service";
    }

    @ReadTx
    @Override
    public List<SampleDto> listSampleDto(String name) {
        return sampleMapper.listSample(name);
    }

}
