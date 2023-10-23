package com.example.demo.respository;

import java.util.List;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(@NonNull String email);
}
