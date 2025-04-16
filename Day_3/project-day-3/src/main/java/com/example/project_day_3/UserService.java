package com.example.project_day_3;

import java.util.List;

public interface UserService {
    public String add_user(UserDto userDto);
    public String delete_user(Long id);
    public List<UserDto> all_users();
    public String update_user(Long id, UserDto userDto);
}
