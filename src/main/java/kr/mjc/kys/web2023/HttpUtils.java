package kr.mjc.kys.web2023;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class HttpUtils {
    public static String getRequestURLWithQueryString(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        return queryString == null ? requestURL.toString() :
                requestURL.append("?").append(queryString).toString();
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp,
                               String path) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp%s.jsp".formatted(path)).forward(req, resp);
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        forward(req, resp, req.getPathInfo());
    }

    public static void redirect(HttpServletRequest req, HttpServletResponse resp,
                                String path) throws IOException {
        resp.sendRedirect(req.getContextPath() + path);
    }

    public static String encodeUrl(String url) {
        return URLEncoder.encode(url, Charset.defaultCharset());
    }
}
