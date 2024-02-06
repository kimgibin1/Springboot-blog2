package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// 자동으로 bean(프레임워크 관리객체)등록이 됨 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
