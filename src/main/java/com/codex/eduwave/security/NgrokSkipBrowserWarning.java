package com.codex.eduwave.security;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NgrokSkipBrowserWarning implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(NgrokSkipBrowserWarning.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("ngrok-skip-browser-warning", "true");
        httpServletResponse.setHeader("User-Agent", "CustomUserAgent");
        logger.info("ngrok-skip-browser-warning header added");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}