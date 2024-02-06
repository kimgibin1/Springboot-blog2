package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; // 시퀀스, auto_increament (SQL)
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne  // 하나의(One) 게시글에 여러개(Many)의 답변 
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}










