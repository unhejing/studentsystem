package com.unhejing.dao.impl;

import com.unhejing.dao.StudentDao;
import com.unhejing.domain.Student;
import com.unhejing.service.RedisService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * com.unhejing.dao.impl
 *
 * @author unhejing
 * @create 2018-04-16 上午11:14
 **/

public class StudentDaoImpl implements StudentDao{


    private Map<String, String> obj2Map(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }


    @Override
    public List<Map<String,String>> getAllStudentsByPage(int pageNum, int showPage) throws SQLException {
        RedisService rs = new RedisService();
        //获取所有的以stu开头的key集合
//        Set<String> keys = rs.getKeys("stu_*");
//        System.out.println(keys);
        //获取stu_score集合,0-9=>showPage-1, 10-19=>(showPage-1)*10
        int start = (showPage-1)*pageNum;
        int end = start+pageNum-1;
        Set<String> keys= rs.getDescSortSet("stu_score",start,end);

        //通过获取的key集合，循环查找hash中对应的所有域和值。--->即查找出所有的学生信息
        Map<String, String> student = new HashMap<>();
        List<Map<String, String>> studentList = new ArrayList<>();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
            student = rs.gethash("stu_"+str);
            System.out.println("转换前："+student.get("birthday"));


            String dt=student.get("birthday");
            SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
            try {
                student.put("birthday",sdf2.format(sdf1.parse(dt)));
                System.out.println("转换后："+sdf2.format(sdf1.parse(dt)));
            } catch (ParseException e) {
                e.printStackTrace();
            }



//            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String dateString = formatter1.format(new Date());
//            System.out.println(dateString);

            studentList.add(student);
        }
        System.out.println(studentList);
        return studentList;
    }

    @Override
    public Boolean addOrUpdateStudent(Student student) throws SQLException {
        //添加学生
        Map<String, String> studentMap = new HashMap<String, String>();
        try {
            studentMap = BeanUtils.describe(student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        studentMap = obj2Map(student);
        RedisService rs = new RedisService();

        try {
            rs.addhash("stu_"+student.getId(),studentMap);
            System.out.println(studentMap);
            //将id存在sorted set中，方便后续排序等操作。
            rs.addSortSet("stu_score",student.getId(),student.getAvgscore());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String,String> getStudentById(String id) throws SQLException {
        //获取学生
        RedisService rs = new RedisService();
        Map<String, String> studentMap = new HashMap<>();
        studentMap = rs.gethash("stu_"+id);

        String dt=studentMap.get("birthday");
        SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
        try {
            studentMap.put("birthday",sdf2.format(sdf1.parse(dt)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return studentMap;
    }

    @Override
    public Boolean deleteStudentById(String id) throws SQLException {
        RedisService rs = new RedisService();
        rs.deleteKey("stu_"+id);
        rs.deleteSortSet("stu_score",id);
        return true;
    }

    @Override
    public long getStudentCount() throws SQLException {
        RedisService rs = new RedisService();
        return rs.getSortSetAllCount("stu_score");
    }

}
