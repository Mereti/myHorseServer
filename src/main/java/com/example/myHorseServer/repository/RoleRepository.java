package com.example.myHorseServer.repository;

import com.example.myHorseServer.model.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Cacheable
    Optional<Role> findByRoleName(String name);
}
