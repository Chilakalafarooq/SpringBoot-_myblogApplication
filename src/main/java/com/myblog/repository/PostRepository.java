package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entite.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
