package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    // Comment, Article 데이터를 가져오기 위함
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    // 특정 게시글의 댓글 목록 조회
    public List<CommentDto> comments(Long articleId) {
        // 댓글 목록 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // Entity -> Dto 반환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i< comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }

        // Dto 반환
        return dtos;
    }

    // 댓글 생성

    // 댓글 수정

    // 댓글 삭제
}
