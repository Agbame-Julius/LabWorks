package com.project.Product_Management_System.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;


@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(String.valueOf(LoggingInterceptor.class));


    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
        logger.info("Incoming Request.. Method = "+ request.getMethod() + ",  url = " + request.getRequestURL());
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Controller method executed: Status = " + response.getStatus());
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            logger.info("Request completed with error: {}" + ex.getMessage());
        } else {
            logger.info("Request completed successfully: Status = "+ response.getStatus());
        }
    }
}
