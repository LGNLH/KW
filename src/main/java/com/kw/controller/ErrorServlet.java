package com.kw.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error-handler")
public class ErrorServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 获取原始请求的异常信息
            Exception ex = (Exception) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

            // 记录异常信息
            log("Exception: " + ex.getMessage(), ex);

            // 设置响应内容类型
            resp.setContentType("text/html;charset=UTF-8");

            // 可选：设置状态码
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            // 渲染错误页面或发送错误信息
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>Internal Server Error</h1>");
            out.println("<p>Sorry, an error occurred: " + ex.getMessage() + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            // 如果错误处理过程中出现异常，直接发送状态码
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error handling error");
        }
    }
}
