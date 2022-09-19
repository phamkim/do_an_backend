package com.healer.do_an_backend.controller;

import com.healer.do_an_backend.dto.ProductDto;
import com.healer.do_an_backend.dto.UserDto;
import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        try {
            List<UserDto> result = userService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        try {
            Optional<UserDto> result = userService.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user found", result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid UserDto userDto) {
        try {
            UserDto result = userService.save(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody @Valid UserDto userDto, @PathVariable UUID id) {
        try {
            Optional<UserDto> user = userService.findById(id);
            if (user.isPresent()) {
                UserDto result = userService.update(userDto, id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user founded", user));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable UUID id) {
        try {
            Optional<UserDto> user = userService.findById(id);
            if (user.isPresent()) {
                userService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", user));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user founded", user));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
