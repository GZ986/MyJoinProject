package service;

import entity.ExamPage;
import entity.ExamPaper;

import java.sql.SQLException;
import java.util.List;

public interface AdminPaperService {


    /**
     * 增加试题
     */
    public int addPaper(ExamPaper paper) throws SQLException;

    /**
     * 删除试题
     */
    public int deletePaper(ExamPaper paper) throws SQLException;

    /**
     * 修改试题
     */
    public int updatePaper(ExamPaper paper) throws SQLException;

}
