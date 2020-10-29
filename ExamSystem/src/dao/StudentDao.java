package dao;
import entity.Student;
import entity.StudentScore;

import java.sql.SQLException;
import java.util.List;
public interface StudentDao  {

    /**
     * 插入学生信息（将学生注册的信息存入数据库）
     */
    public int addStudent(Student stu) throws SQLException;


    /**
     *查询学生个人信息（学生端）
      * @return
     */
    public List<Student> getStudent() throws ClassNotFoundException, SQLException;


    /**
     * 学生修改密码
     */
    public int updatePassword(Student stu) throws SQLException;


    /**
     * 插入学生考试成绩（将学生考试的成绩传给数据库）
     */
     public int addStudentScore(StudentScore ss) throws SQLException;


    /**
     * 查询学生个人成绩（学生查询自己的成绩）
     * @return
     */
     public List<StudentScore> getStudentOwnScore() throws SQLException;


}
