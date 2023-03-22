package com.aaron.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}