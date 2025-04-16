package com.example.Intern_First_Project;

import java.util.List;

public interface ServiceUser {

    public Dto create_user(Dto dto);
    public String delete_user(Long id);
    public List<Dto> see_all_user();
    public String update_user_name(Long id,String name);
}
