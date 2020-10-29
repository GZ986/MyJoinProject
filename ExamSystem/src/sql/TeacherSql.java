package sql;

public class TeacherSql {
    /**
     * 查询所有学生成绩及信息（分页查询）
     */
    public static String checkStudentSql = "SELECT * FROM student_score";


    /**
     * 查询所有考试试卷（分页查询）
     */
    public static String checkExamPageSql = "SELECT * FROM exam_page WHERE id BETWEEN ? AND ?";


    /**
     * 增加考试试卷
     */
    public static String addExamPageSql = "INSERT INTO exam_page VALUES(id,name,time,teacher)";


    /**
     * 删除考试试卷
     */
    public static String deleteExamPageSql = "DELETE FROM exam_page WHERE id=?";


    /**
     * 查询试卷中的试题(某一套试卷)
     */
    public static String checkExamPaperSql = "SELECT * FROM exam_paper";

    /**
     * 增加考试试题
     */
    public static String addExamPaperSql = "INSERT INTO exam_paper VALUES(qid,type,content,value)";

    /**
     * 删除考试试题
     */
    public static String deleteExamPaperSql = "DELETE FROM exam_paper WHERE id = ?";

    /**
     * 修改考试试题
     */
    public static  String updateExamPaperSql = "UPDATE exam_paper SET content = ? WHERE id = ?";



}
