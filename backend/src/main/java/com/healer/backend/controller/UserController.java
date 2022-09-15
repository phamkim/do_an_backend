package com.healer.backend.controller;

import com.healer.backend.dto.UserDto;
import com.healer.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable UUID id) {
        Optional<UserDto> userDto = userService.findById(id);
        return userDto.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid UserDto userDto) {
        userDto.setId(null);
        UserDto result = userService.save(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserDto userDto, @PathVariable UUID id) {
        Optional<UserDto> optionalUserDto = userService.findById(id);
        return optionalUserDto.map(result -> {
            userService.save(userDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteById(@PathVariable UUID id) {
        Optional<UserDto> userDto = userService.findById(id);
        return userDto.map(result -> {
            userService.deleteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}