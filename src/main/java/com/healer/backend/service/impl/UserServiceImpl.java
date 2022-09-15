package com.healer.backend.service.impl;

import com.healer.backend.dao.UserRepository;
import com.healer.backend.dto.UserDto;
import com.healer.backend.entities.User;
import com.healer.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(result -> modelMapper.map(result, UserDto.class));
    }

    @Override
    public UserDto update(UserDto userDto, UUID id) {
        User user = userRepository.findById(id).orElse(new User());
        setUser(user, userDto);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();
        setUser(user, userDto);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public void setUser(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setBirthday(userDto.getBirthday());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setAvatar(userDto.getAvatar());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setPermission(userDto.getPermission());
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
