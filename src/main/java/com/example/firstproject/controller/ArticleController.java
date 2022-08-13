package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        log.info("id: " + id);

        // 1: id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("articles", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 Article을 가져옴
        List<Article> ArticleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", ArticleEntityList);

        // 3: 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1: 수정할 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2: 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        // 1: DTO를 Entity로 변환
        Article articleEntity = form.toEntity();

        // 2: Repository에게 Entity를 DB안에 저장하게 함
        // 2-1: DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2: 기존 데이터 값을 갱신
        if (target != null)
            articleRepository.save(articleEntity);

        // 3: 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // 1: 삭제할 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2: Repository에게 Entity를 DB안에 저장하게 함
        if (articleEntity != null) {
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        // 3: 삭제 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
