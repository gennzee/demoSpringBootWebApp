package com.example.demo.service.impl;

import com.example.demo.dao.SampleMapper;
import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.SampleService;
import com.example.demo.service.annotation.ChangeTx;
import com.example.demo.service.annotation.ReadTx;
import com.example.demo.service.upload.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private FileUploaderService fileUploaderService;

    @Override
    public String sampleService() {
        return "test service";
    }

    @ReadTx
    @Override
    public List<SampleDto> listSampleDto(String name) {
        return sampleMapper.listSample(name);
    }

    @ReadTx
    @Override
    public String authenticateUser(SampleUserDto sampleUserDto) {
        //testing
        String passwordShouldBe = passwordEncoder.encode(sampleUserDto.getPassword());
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sampleUserDto.getUsername(), sampleUserDto.getPassword()));
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return "Bearer " + jwt;
    }

    @ChangeTx
    @Override
    public String uploadFile(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                fileUploaderService.uploadFile(file);
            }
        }
        return "Success";
    }

    @ChangeTx
    @Override
    public String deleteFile(String[] fileName) {
        for (String name : fileName) {
            fileUploaderService.deleteFile(name);
        }
        return "Success";
    }

    @ChangeTx
    @Override
    public String uploadCsv(MultipartFile[] files) throws IOException {
        if (Arrays.stream(files).allMatch(x -> x.getOriginalFilename() != null && x.getOriginalFilename().equals("")))
            return "No files was uploaded.";
        if (Arrays.stream(files).anyMatch(x -> x.getOriginalFilename() != null && x.getOriginalFilename().equals("")))
            return "Please add file.";
        if (Arrays.stream(files).anyMatch(x -> x.getOriginalFilename() != null && !x.getOriginalFilename().contains(".csv")))
            return "Wrong file format.";

        List<String> rowsData = new ArrayList<>();
        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("\r\n");
            rowsData.addAll(Arrays.asList(rows));
        }

        List<SampleUserDto> sampleUserDtos = new ArrayList<>();
        for (String rowData : rowsData) {
            String[] columns = rowData.split(",");
            String username = columns[0];
            String password = columns[1];
            SampleUserDto userDto = new SampleUserDto();
            userDto.setUsername(username);
            userDto.setPassword(passwordEncoder.encode(password));
            sampleUserDtos.add(userDto);
        }
        return sampleUserDtos.toString();
    }

}
