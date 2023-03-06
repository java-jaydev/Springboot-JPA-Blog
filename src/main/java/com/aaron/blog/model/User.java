package com.aaron.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert // insert할때 null 인 컬럼을 제외하고 insert 해준다.
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라간다. AUTO, IDENTITY, TABLE
	private Long id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;

	// @ColumnDefault("'user'")
	// DB는 RoleType이라는게 없다.
	// ENUM을 사용했다는걸 알려줘야한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum -> admin, user, manager
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@CreationTimestamp
	private Timestamp updateDate;
	
}
