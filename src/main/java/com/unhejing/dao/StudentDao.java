package com.unhejing.dao;

import com.unhejing.domain.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * com.unhejing.dao
 *
 * @author unhejing
 * @create 2018-04-16 上午11:15
 **/

public interface StudentDao {

        /**
         * 分页显示所有学生
         * @param pageNum
         * @param showPage
         * @return
         * @throws SQLException
         */
        public List getAllStudentsByPage(int pageNum, int showPage) throws SQLException;


        /**
         * 添加/编辑学生信息
         * @param student
         * @return
         * @throws SQLException
         */
        public Boolean addOrUpdateStudent(Student student) throws SQLException;

        /**
         * 通过学生id查询学生信息
         * @param id
         * @return
         * @throws SQLException
         */
        public Map<String,String> getStudentById(String id) throws SQLException;

        /**
         * 通过id删除学生
         * @param id
         * @return
         * @throws SQLException
         */
        public Boolean deleteStudentById(String id) throws  SQLException;

        /**
         * 获取学生的总数
         * @return
         * @throws SQLException
         */
        public long getStudentCount() throws SQLException;


}
