package com.example.demo.dto;

public class JsonErrorDto {

    private int statusCode;
    private String message;

    public JsonErrorDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonErrorDto [statusCode=" + statusCode + ", message=" + message + "]";
    }

}
