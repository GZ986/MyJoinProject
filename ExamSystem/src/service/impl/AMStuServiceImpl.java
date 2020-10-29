package service.impl;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import entity.StudentScore;
import service.AdminStudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AMStuServiceImpl implements AdminStudentService {

    TeacherDao td = new TeacherDaoImpl();


    /**
     * 查看学生信息及成绩
     */
    @Override
    public List<StudentScore> getStudentScore() throws SQLException {

         List<StudentScore> studentScores = new ArrayList<>();
          studentScores =  td.getStudentScore();
         return studentScores;

    }


}
