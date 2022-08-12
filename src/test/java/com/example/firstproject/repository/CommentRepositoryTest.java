package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest      // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // Case 1: 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(4L, "최애 음식은?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "Park", "치킨");
            Comment b = new Comment(2L, article, "Lee", "피자");
            Comment c = new Comment(3L, article, "Kim", "스파게티");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }

        // Case 2: -1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected, comments, "1번 글은 댓글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // Case 1: Park의 모든 댓글 조회
        {
            // 예상
            String nickname = "Park";
            Comment a = new Comment(1L, new Article(4L, "최애 음식은?", "댓글 ㄱ"), nickname, "치킨");
            Comment b = new Comment(4L, new Article(5L, "최애 영화는?", "댓글 ㄱ"), nickname, "아이언맨");
            Comment c = new Comment(7L, new Article(6L, "취미는?", "댓글 ㄱ"), nickname, "축구");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 실제
            List<Comment> actual = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected.toString(), actual.toString(), "Park님의 모든 댓글 출력");
        }

        // Case 2: null의 모든 댓글 조회
        {
            // 예상
            String nickname = null;
            List<Comment> expected = Arrays.asList();

            // 실제
            List<Comment> actual = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected, actual, "null의 댓글은 없음");
        }

        // Case 3: ""의 모든 댓글 조회
        {
            // 예상
            String nickname = "";
            List<Comment> expected = Arrays.asList();

            // 실제
            List<Comment> actual = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected, actual, "''의 댓글은 없음");
        }
    }
}