package com.example.demo.controller;

import com.example.demo.dto.SampleDto;
import com.example.demo.dto.SampleUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sampleApi")
@Api(tags = "Sample APIs")
public interface SampleApi {

    //simple get
    @ApiOperation(value = "Simple Get Method.")
    @GetMapping(value = {"/"})
    String index();

    //get with pathVariable
    @ApiOperation(value = "Simple Get Method with Path Variable.")
    @GetMapping(value = {"/pathVariable/{pathVariable}"})
    String pathVariable(@PathVariable String pathVariable);

    //get with pathVariable and Param
    @ApiOperation(value = "Simple Get Method with Path Variable And Path Param.")
    @GetMapping(value = {"/pathVariableWithPathParam/{pathVariable}"})
    String pathVariableWithPathParam(@PathVariable String pathVariable, @RequestParam String param);

    //get with sampleDto(json object)
    @ApiOperation(value = "Simple Get Method with Dto.")
    @GetMapping(value = {"/sampleDto"})
    SampleDto productParam(@RequestBody SampleDto dto);

    //get sampleDto with mybatis
    @ApiOperation(value = "Simple Get Method with Param for Mybatis testing.")
    @GetMapping(value = {"/getSampleDtoWithMybatis"})
    List<SampleDto> getListSampleDto(@RequestParam String name);

    //authenticate user with spring security and jwt
    @ApiOperation(value = "Simple Post Method with Dto for logging in to get Bearer token.")
    @PostMapping(value = {"/login"})
    String authenticateUser(@RequestBody SampleUserDto sampleUserDto);

    //post uploadFile
    @ApiOperation(value = "Simple Post Method to upload files.")
    @PostMapping(path = {"/uploadFile"}, consumes = {"multipart/form-data"}, produces = {"application/json"})
    String uploadFile(@RequestParam("file") MultipartFile[] files);

    //post deleteFile
    @ApiOperation(value = "Simple Delete Method to delete files.")
    @DeleteMapping(value = {"/deleteFile"})
    String deleteFile(@RequestParam("fileName") String[] fileName);

    //post read csv file
    @ApiOperation(value = "Simple Read CSV method to upload data from csv file.")
    @PostMapping(path = {"/uploadCsv"}, consumes = {"multipart/form-data"}, produces = {"application/json"})
    String uploadCsv(@RequestParam("csvFile") MultipartFile[] files) throws IOException;
}
