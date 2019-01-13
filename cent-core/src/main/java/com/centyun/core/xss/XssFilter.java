package com.centyun.core.xss;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.util.WebUtils;

/**
 * xss过滤器
 * @author yinww
 *
 */

@Order(1)
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter extends OncePerRequestFilter {
    private MultipartResolver multipartResolver;
    private String encoding;

    public XssFilter() {
    }

    @Override
    protected void initFilterBean() throws ServletException {
        XssMultipartResolver resolver = new XssMultipartResolver();
        resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
        resolver.setMaxUploadSize(4096000);
        resolver.setServletContext(getServletContext());
        multipartResolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!StringUtils.isEmpty(encoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }

        boolean multipartRequestParsed = false;
        HttpServletRequest req = new XssRequestWrapper(request);
        if (multipartResolver.isMultipart(req)) {
            req = multipartResolver.resolveMultipart(req);
            multipartRequestParsed = true;
        }

        try {
            filterChain.doFilter(req, response);
        } finally {
            if (multipartRequestParsed) {
                MultipartHttpServletRequest nativeRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                if(nativeRequest != null) {
                    multipartResolver .cleanupMultipart(nativeRequest);
                }
            }
        }
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
