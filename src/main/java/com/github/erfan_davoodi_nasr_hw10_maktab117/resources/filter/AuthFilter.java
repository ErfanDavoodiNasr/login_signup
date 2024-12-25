package com.github.erfan_davoodi_nasr_hw10_maktab117.resources.filter;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.Help.requestDispatcher;

@WebFilter("/index.jsp")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();
        if (session.getAttribute("user") == null) {
            requestDispatcher(
                    "/login.jsp",
                    "message",
                    "at the first you should login",
                    req,
                    resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
