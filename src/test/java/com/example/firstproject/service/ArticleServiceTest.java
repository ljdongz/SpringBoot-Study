package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅
class ArticleServiceTest {
    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "Hello World", "This is a test article");
        Article b = new Article(2L, "Hello World 2", "This is a test article 2");
        Article c = new Article(3L, "Hello World 3", "This is a test article 3");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {   // 존재하는 id 입력
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "Hello World", "This is a test article");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_fail() {     // 존재하지 않는 id 입력
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() { // title, content만 있는 dto
        // 예상
        ArticleForm dto = new ArticleForm(null,"Hello World", "This is a test article");
        Article expected = new Article(4L, "Hello World", "This is a test article");

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_fail() {    // id를 입력한 dto
        // 예상
        ArticleForm dto = new ArticleForm(4L,"Hello World", "This is a test article");
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_success() { // 존재하는 id 업데이트
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(null,"Hello World 1", "This is a test article 1");
        Article expected = new Article(id, "Hello World 1", "This is a test article 1");

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_fail_1() {  // 존재하지 않는 id 업데이트
        // 예상
        Long id = -1L;
        ArticleForm dto = new ArticleForm(null,"Hello World 1", "This is a test article 1");
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_fail_2() {  // id만 있는 dto
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(null,null, null);
        Article expected = new Article(id,"Hello World", "This is a test article");;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }


    @Test
    @Transactional
    void delete_success() { // 존재하는 id 삭제
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "Hello World", "This is a test article");

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_fail() {    // 존재하지 않는 id 삭제
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected, article);
    }

}