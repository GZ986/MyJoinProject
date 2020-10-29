package dao.impl;

import dao.StudentDao;
import entity.Student;
import entity.StudentScore;
import util.C3P0Util;
import sql.StudentSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {

      Connection  conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

    /**
     * 插入学生信息（将学生注册的信息存入数据库）
     */
    @Override
    public int addStudent(Student stu) throws SQLException {
        conn = C3P0Util.getConnection();  // 从连接池中获取一个连接
        assert conn != null;
        ps = conn.prepareStatement(StudentSql.addStudentSql);  //执行sql语句，实现插入操作
        ps.setInt(1,stu.getNumber());
        ps.setString(2,stu.getUsername());
        ps.setString(3,stu.getCollege());
        ps.setString(4,stu.getMajor());
        ps.setString(5,stu.getPassword());
        int rows = ps.executeUpdate();
        C3P0Util.close(conn,ps,rs);
        return rows;
    }


    /**
     * 查询学生个人成绩（学生查询自己的成绩）
     * @return
     */
    @Override
    public List<StudentScore> getStudentOwnScore() throws SQLException{

        List<StudentScore> studentScores = new ArrayList<>();
        Connection conn = C3P0Util.getConnection();
        System.out.println("StudentDaoImpl "+conn);
        assert conn != null;
        ps = conn.prepareStatement(StudentSql.checkOwnScoreSql);   //执行sql语句(查询学生成绩)
        rs = ps.executeQuery();            //得到sql结果集
        while (rs.next()){
            int number = rs.getInt(1);
            String username = rs.getString(2);
            String college = rs.getString(3);
            String major = rs.getString(4);
            String score = rs.getString(5);

            System.out.println(number);
            System.out.println(username);
            System.out.println(college);
            System.out.println(major);
            System.out.println(score);

            StudentScore studentScore = new StudentScore(number,username,college,major,score);
            studentScores.add(studentScore);

        }
        C3P0Util.close(conn,ps,rs);
        return studentScores;
    }


    /**
     * 插入学生考试成绩（将学生考试的成绩传给数据库）
     */
    @Override
    public int addStudentScore(StudentScore ss) throws SQLException {
        conn = C3P0Util.getConnection();  // 从连接池中获取一个连接
        assert conn != null;
        ps = conn.prepareStatement(StudentSql.addOwnScoreSql);  //执行sql语句，实现插入操作
        ps.setInt(1,ss.getNumber());
        ps.setString(2,ss.getUsername());
        ps.setString(3,ss.getCollege());
        ps.setString(4,ss.getMajor());
        ps.setString(5,ss.getScore());
        int rows = ps.executeUpdate();
        C3P0Util.close(conn,ps,rs);
        return  rows;
    }


    /**
     * 查询学生个人信息（学生端）
     *
     * @return
     */
    @Override
    public List<Student> getStudent() throws ClassNotFoundException, SQLException {
        List<Student> students = new ArrayList<>();
        Connection  conn = C3P0Util.getConnection();
        System.out.println("StudentDaoImpl "+conn);
        assert conn != null;
        ps = conn.prepareStatement(StudentSql.checkStudentSql);   //执行sql语句
        rs = ps.executeQuery();            //得到sql结果集
        while (rs.next()){
            int number = rs.getInt(1);
            String username = rs.getString(2);
            String college = rs.getString(3);
            String major = rs.getString(4);
            String password = rs.getString(5);

            System.out.println(number);
            System.out.println(username);
            System.out.println(college);
            System.out.println(major);
            System.out.println(password);

            Student student = new Student(number,username,college,major,password);
            students.add(student);

        }
        C3P0Util.close(conn,ps,rs);
        return students;
    }

    /**
     * 学生修改密码
     *
     * @param stu
     */
    @Override
    public int updatePassword(Student stu) throws SQLException {
            conn = C3P0Util.getConnection();
            assert conn != null;
            ps = conn.prepareStatement(StudentSql.updatePasswordSql);
            ps.setString(5,stu.getPassword());
            int rows = ps.executeUpdate();
            C3P0Util.close(conn,ps,rs);
            return rows;
    }

}
