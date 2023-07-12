package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.UserVm;
import com.example.lionprintfirstproject.dto.user.CreateUser;
import com.example.lionprintfirstproject.dto.user.LoginDto;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.user.TokenResult;
import com.example.lionprintfirstproject.dto.user.UpdateUser;
import com.example.lionprintfirstproject.entity.User;
import com.example.lionprintfirstproject.mapper.UserMapper;
import com.example.lionprintfirstproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    @PostMapping
    public ResponseData<UserVm> create(@RequestBody @Valid CreateUser dto) {
        User user = service.create(dto);
        return ResponseData.of(mapper.asUserVm(user));
    }

    @PostMapping("/login")
    public ResponseData<TokenResult> login(@RequestBody LoginDto dto) {
        TokenResult tokenResult = service.login(dto);
        return ResponseData.of(tokenResult);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UpdateUser dto, @PathVariable Long id) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
