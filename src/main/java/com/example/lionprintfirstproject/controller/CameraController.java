package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.service.CameraService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cameras")
public class CameraController {
    private final CameraService service;

    @PostMapping("/arrival")
    public void detectFaceArrival(HttpServletRequest request) {
        service.detectFaceArrival(request);
    }

    @PostMapping("/exit")
    public void detectFaceExit(HttpServletRequest request) {
        service.detectFaceExit(request);
    }

}
