package com.example.Spring.Intro.service;

import com.example.Spring.Intro.repository.UserRepo;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.model.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
     private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public String add_user(UserDto userDto) {

        User new_user = new User();
        new_user.setName(userDto.getName());
        try {
            userRepo.save(new_user);
            return "Save successfully";
        } catch (Exception e) {
            return "Rubel ex : "+e.getMessage();
        }
    }

    @Override
    public String delete_user(Long id) {
        if(userRepo.existsById(id))
        {
            userRepo.deleteById(id);
            return "Delete Successfully";
        }
        else return "This user does not exist in database";

    }

    @Override
    public List<UserDto> all_users() {
        List<User> all_users = userRepo.findAll();
        //return all_users.stream().map(UserMapper::mapDto).collect(Collectors.toList());
        return all_users.stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String update_user(Long id, UserDto userDto) {
        //UserEntity user = new UserEntity(id,userDto.getName());
        if(!userRepo.existsById(id)) return "This user does not exist in database";
        else
        {
            User user = userRepo.findById(id).get();
            user.setName(userDto.getName());
            try {
                userRepo.save(user);
                return "Successfully Updated User";
            } catch (Exception e) {
                return "Not Successfully Updated User";
            }
        }


    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id).get();
        UserDto userDto = userMapper.mapToDto(user);
        return userDto;
    }

}
