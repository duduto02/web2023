package kr.mjc.kys.web2023.springmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.mjc.kys.web2023.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SigninInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("me_userId");
        if(userId != null)
            return true;

        String redirectUrl = HttpUtils.getRequestURLWithQueryString(request);
        log.debug("redirectUrl={}", redirectUrl);

        HttpUtils.redirect(request, response,
                "/app/user/signinForm?redirectUrl=" + HttpUtils.encodeUrl(redirectUrl));
        return false;
    }
}
