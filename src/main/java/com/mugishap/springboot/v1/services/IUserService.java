package com.mugishap.springboot.v1.services;

import com.mugishap.springboot.v1.enums.ERole;
import com.mugishap.springboot.v1.enums.EUserStatus;
import com.mugishap.springboot.v1.models.File;
import com.mugishap.springboot.v1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;



public interface IUserService {

    public List<User> getAll();

    public Page<User> getAll(Pageable pageable);

    public User getById(Long id);

    public User create(User user);

    public User update(Long id, User user);

    public boolean delete(Long id);

    public List<User> getAllByRole(ERole role);

    public Page<User> getAllByRole(Pageable pageable, ERole role);

    public List<User> searchUser(String searchKey);

    public Page<User> searchUser(Pageable pageable, String searchKey);

    public User getLoggedInUser();

    public User getByEmail(String email);

    public User changeStatus(Long id, EUserStatus status);

    public User changeProfileImage(Long id, File file);

}