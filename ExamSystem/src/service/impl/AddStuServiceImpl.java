package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import entity.StudentScore;
import service.AddStudentService;

import java.sql.SQLException;

public class AddStuServiceImpl implements AddStudentService {
//    Connection conn = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;

    StudentDao sd = new StudentDaoImpl();

    /**
     * 插入学生信息（将学生注册的信息存入数据库）
     *
     * @param stu
     */
    @Override
    public int addStudent(Student stu) throws SQLException {
        int a = 0;
        a = sd.addStudent(stu);
        return  a;

    }

    /**
     * 插入学生考试成绩（将学生考试的成绩传给数据库）
     *
     * @param ss
     */
    @Override
    public int addStudentScore(StudentScore ss) throws SQLException {
        int a = 0;
        a = sd.addStudentScore(ss);
        return  a;

    }

}
