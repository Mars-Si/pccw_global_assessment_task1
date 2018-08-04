package com.pccwglobal.assessment.marssi.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pccwglobal.assessment.marssi.pojo.User;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, String> {

}
