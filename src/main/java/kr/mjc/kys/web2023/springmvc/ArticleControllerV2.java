package kr.mjc.kys.web2023.springmvc;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import kr.mjc.kys.web2023.HttpUtils;
import kr.mjc.kys.web2023.dao.Article;
import kr.mjc.kys.web2023.dao.ArticleDao;
import kr.mjc.kys.web2023.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Controller
@AllArgsConstructor
public class ArticleControllerV2 {
    private static final String CURRENT_ARTICLE_LIST = "CURRENT_ARTICLE_LIST";
    private final ArticleDao articleDao;

    @GetMapping("/article/articleList")
    public void articleList(HttpServletRequest req, Limit limit, Model model) {
        req.getSession().setAttribute(CURRENT_ARTICLE_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("articleList", articleDao.listArticle(limit));
    }

    @GetMapping("/article/articleForm")
    public void mapDefault() {}

    @PostMapping("/article/addArticle")
    public String addArticle(Article article,
                           @SessionAttribute("me_userId") int userId,
                           @SessionAttribute("me_name") String name) {
        article.setUserId(userId);
        article.setName(name);
        articleDao.addArticle(article);
        return "redirect:/app/article/articleList";
    }

    @GetMapping("/article/article")
    public void article(int articleId, Model model) {
        model.addAttribute("article", articleDao.getArticle(articleId));
    }

    @GetMapping("/article/articleEdit")
    public void articleEdit(int articleId,
                            @SessionAttribute("me_userId") int userId, Model model) {
        Article article = getUserArticle(articleId, userId);
        model.addAttribute("article", article);
    }

    @PostMapping("/article/updateArticle")
    public String updateArticle(Article article,
                                @SessionAttribute("me_userId") int userId) {
        getUserArticle(article.getArticleId(), userId);
        article.setUserId(userId);
        articleDao.updateArticle(article);
        return "redirect:/app/article/article?articleId=" + article.getArticleId();
    }

    @GetMapping("/article/deleteArticle")
    public String deleteArticle(int articleId,
                                @SessionAttribute("me_userId") int userId,
                                @SessionAttribute(CURRENT_ARTICLE_LIST) String currentArticleList) {
        getUserArticle(articleId, userId);
        articleDao.deleteArticle(articleId, userId);
        return "redirect:" + currentArticleList;
    }

    private Article getUserArticle(int articleId, int userId) {
        try {
            return articleDao.getUserArticle(articleId, userId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
