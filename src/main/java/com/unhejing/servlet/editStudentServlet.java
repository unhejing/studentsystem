package com.unhejing.servlet;

import com.unhejing.domain.Student;
import com.unhejing.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/editStudent")
public class editStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String avgscore = request.getParameter("avgscore");
        String description = request.getParameter("description");
        System.out.println("name:"+name+"\nbirthday"+birthday+"\navgscore"+avgscore+"\ndescription"+description);

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName(name);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayObj = null;
        try {
            birthdayObj=simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(birthdayObj);
        student.setBirthday(birthdayObj);
        student.setAvgscore(Integer.parseInt(avgscore));
        student.setDescription(description);
        student.setId(id);

        System.out.println(student);

        //存储到数据库
        boolean flag = false;
        StudentService studentService = new StudentService();
        try {
            flag = studentService.addOrUpdateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flag==true){
            request.setAttribute("code", 1);
            request.setAttribute("msg", "修改成功");
            request.setAttribute("url", "/studentlist?showPage=1");
            request.getRequestDispatcher("/admin/jump.jsp").forward(request, response);
        }else{
            request.setAttribute("code", 2);
            request.setAttribute("msg", "修改失败");
            request.setAttribute("url", "/addStudent");
            request.getRequestDispatcher("/admin/jump.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行editStudent的servlet。。。。");
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("id");
        Map<String, String> studentMap = new HashMap<>();
        StudentService studentService = new StudentService();
        try {
            studentMap = studentService.getStudentById(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("需要编辑的学生信息："+studentMap);
        request.setAttribute("student",studentMap);
        request.getRequestDispatcher("admin/editStudent.jsp").forward(request, response);
    }
}
