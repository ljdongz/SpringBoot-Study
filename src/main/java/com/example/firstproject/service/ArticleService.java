package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service    // 서비스 선언 (서비스 객체를 스프링 부트에 선언)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: 업데이트 대상 Entity 가져옴
        Article target = articleRepository.findById(id).orElse(null);

        // 2: 예외 처리 (업데이트 대상이 없음)
        if (target == null) {
            return null;
        }

        // 3: 수정할 DTO 객체를 Entity로 변환
        Article updated = dto.toEntity();

        // 4: 업데이트 및 정상 응답
        target.patch(updated);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        // 1: 삭제 대상 Entity 가져옴
        Article target = articleRepository.findById(id).orElse(null);

        // 2: 예외 처리 (삭제 대상이 없음)
        if (target == null) {
            return null;
        }

        // 3: 삭제 및 정상 응답
        articleRepository.delete(target);
        return target;
    }

    @Transactional  // 해당 메소드를 트랜잭션으로 묶음 (트랜잭션 실패 시 이전 상태로 롤백)
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

//        List<Article> articleList = new ArrayList<>();
//        for (int i=0; i < dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // Entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

//        for (int i = 0; i < articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("걸재 실패!")
        );

        // 결과 값 반환
        return articleList;
    }
}
