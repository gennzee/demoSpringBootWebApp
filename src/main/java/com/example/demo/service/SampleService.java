package com.example.demo.service;

import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SampleService {

    String sampleService();

    List<SampleDto> listSampleDto(String name);

    String authenticateUser(SampleUserDto sampleUserDto);

    String uploadFile(MultipartFile[] files);

    String deleteFile(String[] fileName);

    String uploadCsv(MultipartFile[] files) throws IOException;

}
