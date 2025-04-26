package com.example.Intern_First_Project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImplement implements ServiceUser {
    @Override
    public Dto create_user(Dto dto) {
        return null;
    }

    @Override
    public String delete_user(Long id) {
        return "";
    }

    @Override
    public List<Dto> see_all_user() {
        return List.of();
    }

    @Override
    public String update_user_name(Long id, String name) {
        return "";
    }
}
