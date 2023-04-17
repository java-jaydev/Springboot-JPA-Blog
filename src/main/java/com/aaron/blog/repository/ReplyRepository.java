package com.aaron.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aaron.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	// 인터페이스에서는 public 생략 가능
	@Modifying
	@Query(value="INSERT INTO Reply (userId, boardId, content, createDate) VALUES (?1, ?2, ?3, now())", nativeQuery = true)
	void nativeInsertReply(Long userId, Long boardId, String content);
}
