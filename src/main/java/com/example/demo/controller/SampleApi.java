package com.example.demo.controller;

import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sampleApi")
public interface SampleApi {

    //simple get
    @GetMapping(value = {"/"})
    String index();

    //get with pathVariable
    @GetMapping(value = {"/pathVariable/{pathVariable}"})
    String pathVariable(@PathVariable String pathVariable);

    //get with pathVariable and Param
    @GetMapping(value = {"/pathVariableWithPathParam/{pathVariable}"})
    String pathVariableWithPathParam(@PathVariable String pathVariable, @RequestParam String param);

    //get with sampleDto(json object)
    @GetMapping(value = {"/sampleDto"})
    SampleDto productParam(@RequestBody SampleDto dto);

    //get sampleDto with mybatis
    @GetMapping(value = {"/getSampleDtoWithMybatis"})
    List<SampleDto> getListSampleDto(@RequestParam String name);

    //authenticate user with spring security and jwt
    @PostMapping(value = {"/login"})
    String authenticateUser(@RequestBody SampleUserDto sampleUserDto);

    //post with FormData type
    @PostMapping(path = {"/postFormData"}, consumes = {"multipart/form-data"}, produces = {"application/json"})
    String postFormData(@RequestParam Map<String, String> m, @RequestParam("file") MultipartFile[] files);

}
