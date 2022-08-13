package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 해당 댓글 엔티티 여러개가 하나의 Article에 연관
    @JoinColumn(name = "article_id")    // fk 이름
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(Article article, CommentDto dto) {
        // 예외 처리
        if (dto.getId() !=  null)
            throw new IllegalArgumentException("id 값은 넣을 수 없습니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("articleId 값이 일치하지 않습니다.");

        // 엔티티 생성 및 반환
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }
}
