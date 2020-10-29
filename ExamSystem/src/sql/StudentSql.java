package sql;

public class StudentSql {

    /**
     * 插入学生信息（将学生注册的信息存入数据库）
     */
    public static String addStudentSql = "INSERT INTO student VALUES(number,username,college,major,password)";


    /**
     * 查询学生个人信息（学生查询自己个人信息）
     */
    public static String checkStudentSql = "SELECT * FROM student WHERE number='2019013630' ";

    /**
     * 修改学生个人信息（即学生修改自己密码）
     */
    public static String updatePasswordSql = "UPDATE student SET password='用户发来的新密码' WHERE number = '用户登录时发来的number' ";


    /**
     * 插入学生考试成绩（将学生考试的成绩传给数据库）
     */
    public static String addOwnScoreSql = "INSERT INTO student_score VALUES(number,username,college,major,score)";


    /**
     * 查询学生个人成绩（学生查询自己的考试成绩）
     */
    public static String checkOwnScoreSql = "SELECT * FROM student_score WHERE number = '2019013630' ";


    /**
     * 根据学生学号查询密码
     */
    public static String checkStuPWSql = "SELECT password FROM student WHERE number = '2019013630'";

    /**
     * 分页查询考试试卷(记录数)
     */
    public static String checkPageSql = "SELECT COUNT(*) FROM exampasge";

}
