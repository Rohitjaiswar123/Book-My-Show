package com.example.BookMyShow.repositories;

import com.example.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    JpaRepository
    @Override
    Optional<User> findById(Long userId);
//    find the user by given userId
//    findbyid means this method is inside (userRepsitory) is expected to return a user object when we pass userId


    @Override
    List<User> findAllById(Iterable<Long> longs);
//    select * from users where user_id IN(1,2,3,4,5,6);

}
