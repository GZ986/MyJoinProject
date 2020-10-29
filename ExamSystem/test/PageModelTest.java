//package test;
//
//import entity.ExamPage;
//import util.C3P0Util;
//import util.PageModel;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PageModelTest {
//    public static void main(String[] args){
//        int pageNo = 1;
//        int pageSize = 4;
//        findExamPageList(pageNo,pageSize);
//    }
//
//    /**
//     * 分页查询
//     * @param pageNo 第几页
//     * @param pageSize 每一页有多少条记录
//     * @return PageModel<E>
//     */
//     public static PageModel findExamPageList(int pageNo, int pageSize){
//         PageModel pageModel = null;
//         Connection conn = null;
//         PreparedStatement ps = null;
//         ResultSet rs = null;
//         String sql = "SELECT * FROM exam_page ORDER BY id LIMIT ?,?";
//         try {
//             conn = C3P0Util.getConnection();
//             ps = conn.prepareStatement(sql);
//             ps.setInt(1,(pageNo-1)*pageSize);
//             ps.setInt(2,pageSize);
//             rs = ps.executeQuery();
//             List<ExamPage> examPages = new ArrayList<>();
//             while(rs.next()){
//                 ExamPage examPage = new ExamPage();
//                 examPage.setName(rs.getString("name"));
//                 examPage.setTime(rs.getString("time"));
//                 examPage.setTeacher(rs.getString("teacher"));
//                 examPages.add(examPage);
//             }
//             pageModel = new PageModel();
//             pageModel.setList(examPages);
//             pageModel.setTotalRecords(getTotalRecords(conn));
//             pageModel.setPageSize(pageSize);
//             pageModel.setPageNo(pageNo);
//
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }finally {
//             C3P0Util.close(conn,ps,rs);
//         }
//
//        return pageModel;
//     }
//
//    /**
//     * 得到总记录数
//     * @param conn
//     * @return
//     */
//
//     private static int getTotalRecords(Connection conn){
//         PreparedStatement ps = null;
//         ResultSet rs = null;
//         String sql = "SELECT *  COUNT(*) FROM exam_page";
//         conn = C3P0Util.getConnection();
//         int count = 0;
//         try{
//             ps = conn.prepareStatement(sql);
//             rs = ps.executeQuery();
//             while (rs.next()){
//                 count = rs.getInt(1);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }finally {
//             C3P0Util.close(conn,ps,rs);
//         }
//         return  count;
//     }
//
//
//}
