package com.example.demo.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    Group findByGroupname (String groupname);

    //@Query(value = "select g FROM tbl_group g")
    //List<Group> getAllGroups;
}
