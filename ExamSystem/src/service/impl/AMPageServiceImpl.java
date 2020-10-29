package service.impl;

import dao.impl.TeacherDaoImpl;
import entity.ExamPage;
import service.AdminPageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AMPageServiceImpl implements AdminPageService {

     TeacherDaoImpl tdi = new TeacherDaoImpl();

    /**
     * 查询考试试卷
     */
    @Override
    public List<ExamPage> getExamPage() throws ClassNotFoundException, SQLException {
        List<ExamPage> examPageList = new ArrayList<>();
        examPageList = tdi.getExamPage();
        return examPageList;
    }

    /**
     * 增加试卷
     *
     * @param page
     */
    @Override
    public int addPage(ExamPage page) throws SQLException {
         int p = 0;
         p = tdi.addPage(page);
         return p;

    }

    /**
     * 删除试卷
     *
     * @param page
     */
    @Override
    public int deletePage(ExamPage page) throws SQLException {
          int p = 0;
          p = tdi.deletePage(page);
          return p;
    }


}
