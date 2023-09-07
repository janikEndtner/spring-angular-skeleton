package com.example.demo.respository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(@NonNull String email);
}
