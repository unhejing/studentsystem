package com.unhejing.servlet;

import com.unhejing.domain.Student;
import com.unhejing.service.StudentService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/studentlist")
public class ShowAllStudentsByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = 10;
//        int showPage = !"".equals(request.getParameter("showPage")) ? Integer.parseInt(request.getParameter("showPage")) : 1;
        int showPage = Integer.parseInt(request.getParameter("showPage"));
        System.out.println(showPage);
        StudentService studentService = new StudentService();
        List<Map<String, String>> studentList = new ArrayList<>();
        try {
            studentList = studentService.getAllStudentsByPage(pageNum,showPage);
//            studentService.getAllStudentsByPage(2,1);
            System.out.println("学生列表："+studentList);
            request.setAttribute("studentList",studentList);
            long num = 0;
            num = studentService.getStudentCount();
            System.out.println("总条数："+num+"\n当前页"+showPage);
            request.setAttribute("num",num);
            request.setAttribute("pageNum",showPage);

            request.getRequestDispatcher("admin/showAllStudentsByPage.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
