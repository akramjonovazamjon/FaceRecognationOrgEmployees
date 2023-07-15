package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.dto.CameraResult;
import com.example.lionprintfirstproject.service.CameraService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.Enumeration;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cameras")
public class CameraController {
    private final CameraService service;

    @PostMapping
    public void detectFace(HttpServletRequest request) {
        service.detectFace(request);
    }

}
