package com.example.demo.controller.impl;

import com.example.demo.controller.SampleApi;
import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import com.example.demo.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class SampleApiImpl implements SampleApi {

    @Autowired
    private SampleService sampleService;

    @Override
    public String index(){
        return sampleService.sampleService();
    }

    @Override
    public String pathVariable(String pathVariable){
        return "Path Variable : " + pathVariable;
    }

    @Override
    public String pathVariableWithPathParam(String pathVariable, String param){
        final String PATH_VARIABLE = "Path Variable : " + pathVariable + "<br>";
        final String PARAM_NAME = "Param Name : param" + "<br>";
        final String PARAM_VALUE = "Param Value : " + param + "<br>";
        return PATH_VARIABLE + PARAM_NAME + PARAM_VALUE;
    }

    @Override
    public SampleDto productParam(SampleDto dto){
        return dto;
    }

    @Override
    public List<SampleDto> getListSampleDto(String name) {
        return sampleService.listSampleDto(name);
    }

    @Override
    public String authenticateUser(SampleUserDto sampleUserDto) {
        return sampleService.authenticateUser(sampleUserDto);
    }

    @Override
    public String uploadFile(MultipartFile[] files) {
        return sampleService.uploadFile(files);
    }

    @Override
    public String deleteFile(String[] fileName) {
        return sampleService.deleteFile(fileName);
    }

    @Override
    public String uploadCsv(MultipartFile[] files) throws IOException {
        return sampleService.uploadCsv(files);
    }
}
