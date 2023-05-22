package com.example.websrcapper.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private int status;
    private String description;
    private T data;
}
