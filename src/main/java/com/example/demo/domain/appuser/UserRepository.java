package com.example.demo.domain.appuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername (String username);
    
    @Query(value = "select u.* from tbl_group_users gu JOIN tbl_group tg on gu.tbl_group_id = tg.id join users u on u.id = gu.users_id where groupname = :groupname", nativeQuery = true)
    Page<User> findUsersByGroupname(@Param("groupname") String groupname, Pageable pageable);
    
    User findByEmail(String email);
}
