package service;

import entity.Student;
import entity.StudentScore;

import java.sql.SQLException;
import java.util.List;

public interface CheckStudentService {

    /**
     *查询学生个人信息（学生端）
     * @return
     */
    public List<Student> getStudent() throws ClassNotFoundException, SQLException;


    /**
     * 查询学生个人成绩（学生查询自己的成绩）
     * @return
     */
    public List<StudentScore> getStudentOwnScore() throws SQLException;


}
