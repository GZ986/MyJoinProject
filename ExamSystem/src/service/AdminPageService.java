package service;

import entity.ExamPage;

import java.sql.SQLException;
import java.util.List;

public interface AdminPageService {

    /**
     * 查询试卷
     */
    public List<ExamPage> getExamPage() throws ClassNotFoundException, SQLException;

    /**
     * 增加试卷
     */
    public int addPage(ExamPage pape) throws SQLException;

    /**
     * 删除试卷
     */
    public int deletePage(ExamPage page) throws SQLException;

}
