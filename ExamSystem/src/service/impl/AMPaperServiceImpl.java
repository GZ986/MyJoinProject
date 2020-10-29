package service.impl;

import dao.impl.TeacherDaoImpl;
import entity.ExamPage;
import entity.ExamPaper;
import service.AdminPaperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AMPaperServiceImpl implements AdminPaperService {

    TeacherDaoImpl tdi = new TeacherDaoImpl();

    /**
     * 增加试题
     *
     * @param paper
     */
    @Override
    public int addPaper(ExamPaper paper) throws SQLException {
             int q = 0;
             q = tdi.addPaper(paper);
             return q;
    }

    /**
     * 删除试题
     *
     * @param paper
     */
    @Override
    public int deletePaper(ExamPaper paper) throws SQLException {
             int q = 0;
             q = tdi.deletePaper(paper);
             return q;
    }

    /**
     * 修改试题
     *
     * @param paper
     */
    @Override
    public int updatePaper(ExamPaper paper) throws SQLException {
            int q = 0;
            q = tdi.updatePaper(paper);
            return q;
    }


}
