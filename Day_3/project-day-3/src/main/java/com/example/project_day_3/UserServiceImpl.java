package com.example.project_day_3;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String add_user(UserDto userDto) {
        UserEntity userEntity = UserMapper.mapEntity(userDto);
        UserEntity save_entity = userRepo.save(userEntity);
        if(save_entity == null) return "Not Successfully Added User";
        else return "Successfully Added User";
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
        List<UserEntity> all_users = userRepo.findAll();
        return all_users.stream().map(UserMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String update_user(Long id, UserDto userDto) {
        UserEntity user = new UserEntity(id,userDto.getName());
        UserEntity update_user = userRepo.save(user);
        if(update_user==null) return "Not Successfully Updated User";
        else return "Successfully Updated User";

    }

}
