package com.chamdev.museumproject.controller;

import com.chamdev.museumproject.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/objects")
public class ObjectController {
    public ResponseEntity<ApiResponse> addObject(){
        return ResponseEntity.ok(new ApiResponse("object created", null));
    }
}
