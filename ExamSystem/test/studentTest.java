package test;
import dao.impl.StudentDaoImpl;
import entity.Student;
import entity.StudentScore;
import org.junit.Test;

import java.util.List;

public class studentTest {

    /**
     * 测试查询学生信息
     * @throws Exception
     */
    @Test
    public void TestStudent() throws Exception{
        StudentDaoImpl stuDaoimpl = new StudentDaoImpl();
        List<Student> studentList = stuDaoimpl.getStudent();
        for (Student student: studentList){
            System.out.println(student.toString());
        }
    }


    /**
     * 测试查询学生成绩
     */
    @Test
    public void TestStudent2() throws Exception{
        StudentDaoImpl stuDaoimpl2 = new StudentDaoImpl();
        List<StudentScore> studentScores = stuDaoimpl2.getStudentOwnScore();
        for (StudentScore studentScore: studentScores){
            System.out.println(studentScore.toString());
        }
    }


    /**
     * 测试插入学生信息
     * @throws Exception
     */
    @Test
    public void TestStudent3() throws Exception{
        StudentDaoImpl stuDaoimpl3 = new StudentDaoImpl();
        Student stu = new Student(2019013633,"tql","信科学院","软化工程","444");
        int a = stuDaoimpl3.addStudent(stu);

  }


}
