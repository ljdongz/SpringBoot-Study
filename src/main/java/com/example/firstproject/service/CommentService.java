package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    // Comment, Article 데이터를 가져오기 위함
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    // 특정 게시글의 댓글 목록 조회
    public List<CommentDto> comments(Long articleId) {

//        // 댓글 목록 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // Entity -> Dto 반환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i< comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        // Dto 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));  // 예외 발생 시 코드 실행

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(article, dto);

        // 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        // 댓글 엔티티 -> Dto 반환
        return CommentDto.createCommentDto(created);
    }

    // 댓글 수정
    @Transactional
    public CommentDto update(Long commentId, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));  // 예외 발생 시 코드 실행

        // 조회한 댓글을 수정
        target.patch(dto);

        // 댓글 엔티티를 DB에 저장
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티 -> Dto 반환
        return CommentDto.createCommentDto(updated);
    }

    // 댓글 삭제
    @Transactional
    public CommentDto delete(Long commentId) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));  // 예외 발생 시 코드 실행

        // 댓글 엔티티를 DB에서 삭제
        commentRepository.delete(target);

        // 댓글 엔티티 -> Dto 반환
        return CommentDto.createCommentDto(target);
    }
}
