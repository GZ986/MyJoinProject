package service;

import entity.Student;
import entity.StudentScore;

import java.sql.SQLException;

public interface AddStudentService {

    /**
     * 插入学生信息（将学生注册的信息存入数据库）
     */
    public int addStudent(Student stu) throws SQLException;

    /**
     * 插入学生考试成绩（将学生考试的成绩传给数据库）
     */
    public int addStudentScore(StudentScore ss) throws SQLException;


}
