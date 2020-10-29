package dao;
import entity.ExamPage;
import entity.ExamPaper;
import entity.Student;
import entity.StudentScore;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {

    /**
     * 查看学生成绩及信息
     */
    public List<StudentScore> getStudentScore() throws SQLException;


    /**
     * 查询试卷(分页查询)
     */
    public List<ExamPage> getExamPage() throws ClassNotFoundException,SQLException;


    /**
     * 增加试卷
     */
    public int addPage(ExamPage pape) throws SQLException;


    /**
     * 删除试卷
     */
    public int deletePage(ExamPage page) throws SQLException;


    /**
     * 查询试题
     */
    public List<ExamPaper> getExamPaper() throws SQLException;


    /**
     * 增加试题
     */
    public int addPaper(ExamPaper paper) throws SQLException;


    /**
     * 删除试题
     */
    public int deletePaper(ExamPaper paper) throws SQLException;


    /**
     * 修改试题(也就是修改试卷)
     */
    public int updatePaper(ExamPaper paper) throws SQLException;




}
