package service;

import entity.StudentScore;

import java.sql.SQLException;
import java.util.List;

public interface AdminStudentService {

    /**
     * 查看学生信息及成绩
     */
    public List<StudentScore> getStudentScore() throws SQLException;

}
