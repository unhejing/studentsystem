package com.unhejing.service;

import com.unhejing.dao.impl.StudentDaoImpl;
import com.unhejing.domain.Student;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unhejing
 * @create 2018-04-16 下午1:35
 **/
public class StudentService {


    private RedisService redisService;
    //分页显示所有学生
    public List<Map<String, String>> getAllStudentsByPage(int pageNum, int showPage) throws SQLException{
        StudentDaoImpl sdi = new StudentDaoImpl();
        return sdi.getAllStudentsByPage(pageNum,showPage);
    }

    //添加或编辑学生
    public Boolean addOrUpdateStudent(Student student) throws SQLException{
        StudentDaoImpl sdi = new StudentDaoImpl();
        return sdi.addOrUpdateStudent(student);
    }


    public Map<String,String> getStudentById(String id) throws  SQLException{
        StudentDaoImpl sdi = new StudentDaoImpl();
        Map<String, String> studentMap = new HashMap<>();
        studentMap = sdi.getStudentById(id);
        return studentMap;
    }

    public Boolean deleteStudentById(String id) throws SQLException {
        StudentDaoImpl sdi = new StudentDaoImpl();
        return sdi.deleteStudentById(id);
    }

    public long getStudentCount() throws SQLException {
        StudentDaoImpl sdi = new StudentDaoImpl();
        return sdi.getStudentCount();
    }

}
