package com.mugishap.springboot.v1.repositories;

import com.mugishap.springboot.v1.enums.ERole;
import com.mugishap.springboot.v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
