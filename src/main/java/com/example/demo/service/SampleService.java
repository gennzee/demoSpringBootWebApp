package com.example.demo.service;

import com.example.demo.dto.SampleDto;

import java.util.List;

public interface SampleService {

    String sampleService();

    List<SampleDto> listSampleDto(String name);

}
