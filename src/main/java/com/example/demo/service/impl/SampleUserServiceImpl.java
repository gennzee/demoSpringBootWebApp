package com.example.demo.service.impl;

import com.example.demo.dao.SampleUserMapper;
import com.example.demo.dto.CustomUserDetails;
import com.example.demo.dto.SampleUserDto;
import com.example.demo.service.SampleUserService;
import com.example.demo.service.annotation.ReadTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SampleUserServiceImpl implements SampleUserService, UserDetailsService {

    @Autowired
    private SampleUserMapper sampleUserMapper;

    @ReadTx
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SampleUserDto sampleUserDto = sampleUserMapper.findByUsername(username);
        if (sampleUserDto == null) throw new UsernameNotFoundException(username);
        return new CustomUserDetails(sampleUserDto);
    }

    @ReadTx
    @Override
    public UserDetails loadUserById(int id) throws Exception {
        SampleUserDto sampleUserDto = sampleUserMapper.findByUserId(id);
        if (sampleUserDto == null) throw new Exception("not found user by id");
        return new CustomUserDetails(sampleUserDto);
    }
}
