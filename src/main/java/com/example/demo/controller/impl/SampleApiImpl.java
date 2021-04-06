package com.example.demo.controller.impl;

import com.example.demo.controller.SampleApi;
import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import com.example.demo.service.SampleService;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.upload.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Component
public class SampleApiImpl implements SampleApi {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private SampleService sampleService;

    @Autowired
    private FileUploaderService fileUploaderService;

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
        //testing
        String passwordShouldBe = passwordEncoder.encode(sampleUserDto.getPassword());
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sampleUserDto.getUsername(),sampleUserDto.getPassword()));
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return jwt;
    }

    @Override
    public String postFormData(Map<String, String> m, MultipartFile[] files) {
        for(MultipartFile file : files) {
            if(!file.isEmpty()){
                fileUploaderService.uploadFile(file);
            }
        }
        return null;
    }
}
