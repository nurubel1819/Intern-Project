package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.dto.UserRoleDto;
import com.example.Spring.Intro.model.entity.Role;
import com.example.Spring.Intro.repository.UserRepo;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public String addUser(UserDto userDto) {

        User new_user = new User();
        new_user.setName(userDto.getUsername());
        new_user.setPassword(userDto.getPassword());
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
    public String updateUser(Long id, UserDto userDto) {
        //UserEntity user = new UserEntity(id,userDto.getName());
        if(!userRepo.existsById(id)) return "This user does not exist in database";
        else
        {
            User user = userRepo.findById(id).get();
            user.setName(userDto.getUsername());
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

    @Override
    public String registerUser(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        try {
            userRepo.save(user);
            return "Successfully Registered User";
        } catch (Exception e) {
            return "Not Successfully Registered User";
        }
    }

    @Override
    public UserRoleDto getRoles(Long userId) {
        UserRoleDto userRoleDto = new UserRoleDto();

        if(!userRepo.existsById(userId)) return null;
        else {
            User user = userRepo.findById(userId).get();
            Set<Role> roles = user.getRoles();

            Set<String> allRolesName = new HashSet<>();

            for(Role it : roles) allRolesName.add(it.getRoleName());

            userRoleDto.setUserId(userId);
            userRoleDto.setRoleName(allRolesName);
            return userRoleDto;
        }
    }

}
