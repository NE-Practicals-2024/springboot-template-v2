package com.mugishap.springboot.v1.serviceImpls;

import com.mugishap.springboot.v1.enums.ERole;
import com.mugishap.springboot.v1.enums.EUserStatus;
import com.mugishap.springboot.v1.exceptions.BadRequestException;
import com.mugishap.springboot.v1.exceptions.ResourceNotFoundException;
import com.mugishap.springboot.v1.models.File;
import com.mugishap.springboot.v1.models.User;
import com.mugishap.springboot.v1.repositories.IUserRepository;
import com.mugishap.springboot.v1.services.IUserService;
import com.mugishap.springboot.v1.standalone.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private  final FileStorageService fileStorageService;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id.toString()));
    }

    @Override
    public User create(User user) {
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent())
            throw new BadRequestException(String.format("User with email '%s' already exists", user.getEmail()));

        return this.userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", id.toString()));

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent() && (userOptional.get().getId() != entity.getId()))
            throw new BadRequestException(String.format("User with email '%s' already exists", entity.getEmail()));

        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setMobile(user.getMobile());
        entity.setGender(user.getGender());


        return this.userRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));

        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> getAllByRole(ERole role) {
        return this.userRepository.findByRoles(role);
    }

    @Override
    public Page<User> getAllByRole(Pageable pageable, ERole role) {
        return this.userRepository.findByRoles(pageable, role);
    }

    @Override
    public List<User> searchUser(String searchKey) {
        return this.userRepository.searchUser(searchKey);
    }

    @Override
    public Page<User> searchUser(Pageable pageable, String searchKey) {
        return this.userRepository.searchUser(pageable, searchKey);
    }

    @Override
    public User getLoggedInUser() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email = principal.toString();
        }

        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));
    }



    @Override
    public User changeStatus(Long id, EUserStatus status) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", id.toString()));

        entity.setStatus(status);

        return  this.userRepository.save(entity);
    }

    @Override
    public User changeProfileImage(Long id, File file) {
        User entity = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Document", "id", id.toString()));
        File existingFile = entity.getProfileImage();
        if (existingFile != null) {
            this.fileStorageService.removeFileOnDisk(existingFile.getPath());
        }

        System.out.println("Here");
        entity.setProfileImage(file);
        return this.userRepository.save(entity);

    }
}
