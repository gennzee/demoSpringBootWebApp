package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SampleUserService {

    UserDetails loadUserById(int id) throws Exception;

}
