package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import entity.StudentScore;
import service.CheckStudentService;
import sql.StudentSql;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckStuServiceImpl implements CheckStudentService {

    StudentDao sd = new StudentDaoImpl();

    /**
     * 查询学生个人信息（学生端）
     *
     * @return
     */
    @Override
    public List<Student> getStudent() throws ClassNotFoundException, SQLException {

        List<Student> students = new ArrayList<>();
        students = sd.getStudent();
        return students;

    }

    /**
     * 查询学生个人成绩（学生查询自己的成绩）
     * @return
     */
    @Override
    public List<StudentScore> getStudentOwnScore() throws SQLException {

        List<StudentScore> studentScores = new ArrayList<>();
        studentScores = sd.getStudentOwnScore();
        return studentScores;

    }

}
