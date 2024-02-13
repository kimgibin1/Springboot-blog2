package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob  // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)  // Many = Board, User = One / 한유저가 여러 게시글 가능
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할수 없음 / FKㅡ 자바는 오브젝트를 저장 가능
	
	@OneToMany(mappedBy =  "board", fetch =  FetchType.EAGER) // mappedBy 연관관계의 주인이 아님 (포링키아님)
	private List<Reply> reply;  // Reply 테이블의 board가 포링키
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
