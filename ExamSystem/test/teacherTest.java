package test;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import entity.ExamPage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class teacherTest {
    /**
     * 测试查询考试试卷
     */
    @Test
    public void TestTeacher() throws Exception{
        TeacherDaoImpl teacherDaoimpl = new TeacherDaoImpl();
        List<ExamPage> examPages = teacherDaoimpl.getExamPage();
        for (ExamPage examPage: examPages){
            System.out.println(examPage.toString());
        }
    }


}
