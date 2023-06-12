package kr.mjc.kys.web2023.springmvc;

import jakarta.servlet.http.HttpSession;
import kr.mjc.kys.web2023.HttpUtils;
import kr.mjc.kys.web2023.dao.Limit;
import kr.mjc.kys.web2023.dao.User;
import kr.mjc.kys.web2023.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@AllArgsConstructor
@Slf4j
public class UserControllerV2 {

    private final UserDao userDao;

    @GetMapping({"/user/signinForm", "/user/signupForm", "/user/myInfo", "/user/passwordEdit"})
    public void mapDefalult() {}

    @GetMapping("/user/userList")
    public void userList(Limit limit, Model model) {
        model.addAttribute("userList", userDao.listUsers(limit));
    }

    @GetMapping("/user/userInfo")
    public void userInfo(int userId, Model model) {
        model.addAttribute("user", userDao.getUser(userId));
    }

    @PostMapping("/user/signup")
    public String signup(User user, String redirectUrl, HttpSession session) {
        try {
            userDao.addUser(user);
            return signin(user.getEmail(), user.getPassword(), "", session);
        } catch (DataAccessException e) {
            log.error(e.getCause().toString());
            return "redirect:/app/user/signupForm?mode=FAILURE";
        }
    }

    @PostMapping("/user/signin")
    public String signin(String email, String password, String redirectUrl,
                         HttpSession session) {
        redirectUrl = StringUtils.defaultIfEmpty(redirectUrl, "/app/user/userList");
        log.debug("redirectUrl=" + redirectUrl);
        try {
            User user = userDao.login(email, password);
            session.setAttribute("me_userId", user.getUserId());
            session.setAttribute("me_name", user.getName());
            session.setAttribute("me_email", user.getEmail());
            return "redirect:" + redirectUrl;
        } catch (DataAccessException e) {
            return "redirect:/app/user/signinForm?mode=FAILURE&redirectUrl=" +
                    HttpUtils.encodeUrl(redirectUrl);
        }
    }
    @PostMapping("/user/updatePassword")
    public String updatePassword(@SessionAttribute("me_userId") int userId,
                                 String currentPassword, String newPassword) {
        int updateRows =
                userDao.updatePassword(userId, currentPassword, newPassword);
        if (updateRows >= 1)
            return "redirect:/app/user/myInfo";
        else
            return "redirect:/app/user/passwordEdit?mode=FAILURE";
    }

    @GetMapping("/user/signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/app/user/userList";
    }
}
