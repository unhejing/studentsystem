package com.unhejing.servlet;

import com.unhejing.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteStudent")
public class deleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        StudentService studentService = new StudentService();
        boolean flag = false;
        try {
            studentService.deleteStudentById(id);
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flag==true){
            request.setAttribute("code", 1);
            request.setAttribute("msg", "删除成功");
            request.setAttribute("url", "/studentlist?showPage=1");
            request.getRequestDispatcher("/admin/jump.jsp").forward(request, response);
        }else{
            request.setAttribute("code", 2);
            request.setAttribute("msg", "删除失败");
            request.setAttribute("url", "/studentlist?showPage=1");
            request.getRequestDispatcher("/admin/jump.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
