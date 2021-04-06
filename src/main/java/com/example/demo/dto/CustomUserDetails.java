package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private SampleUserDto sampleUserDto;

    public CustomUserDetails(SampleUserDto sampleUserDto){
        super();
        this.sampleUserDto = sampleUserDto;
    }

    public SampleUserDto getSampleUserDto() {
        return sampleUserDto;
    }

    public void setSampleUserDto(SampleUserDto sampleUserDto) {
        this.sampleUserDto = sampleUserDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return sampleUserDto.getPassword();
    }

    @Override
    public String getUsername() {
        return sampleUserDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
