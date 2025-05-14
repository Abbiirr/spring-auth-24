package org.example.springauth24;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class RobotFilter extends OncePerRequestFilter {
    private final String HEADER_NAME = "x-robot-password";


    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {
        Collections.list(request.getHeaderNames()).contains(HEADER_NAME);
        if (!Collections.list(request.getHeaderNames()).contains(HEADER_NAME)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String password = request.getHeader("x-robot-password");
        if (!"beep-boop".equals(password)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("nope, you are not the robot 🚫");
            return;
        }
        final SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new RobotAuthentication());
        SecurityContextHolder.setContext(context);
        filterChain.doFilter(request, response);

    }
}
