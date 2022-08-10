package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

// <관리 대상 엔티티, 관리 대상 엔티티의 pk 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
