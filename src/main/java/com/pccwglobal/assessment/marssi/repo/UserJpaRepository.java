package com.pccwglobal.assessment.marssi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pccwglobal.assessment.marssi.pojo.User;

public interface UserJpaRepository extends JpaRepository<User, String> {

}
