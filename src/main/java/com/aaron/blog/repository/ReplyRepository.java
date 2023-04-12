package com.aaron.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
