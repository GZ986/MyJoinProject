package servlet;

import com.google.gson.Gson;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.impl.StudentDaoImpl;
import entity.ExamPage;
import entity.Student;
import entity.StudentScore;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.impl.AddStuServiceImpl;
import service.impl.CheckStuServiceImpl;
import sql.StudentSql;
import util.C3P0Util;
import util.GSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentServlet extends HttpServlet {

    public StudentServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");  //请求编码
        resp.setContentType("text/html;charset=utf-8");  //响应编码
        PrintWriter out = resp.getWriter();      //服务器端向客户端反馈需要用流向客户端输出数据
        String uri = req.getRequestURI();  //获取客户端发来的uri(二级域名)

        System.out.println(req.getRequestURL());
        System.out.println(req.getRequestURI());
        System.out.println(req.getServletPath());
        System.out.println(req.getHeaders("Accept-Encoding"));

        /**
         * 判断请求的uri，以便让不同的方法去处理
         */
        switch (uri){

            case "/StudentServlet/login" :
                 login(req,resp);
                 break;

            case "/StudentServlet/register" :
                try {
                    register(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case"/StudentServlet/checkStu" :
                try {
                    checkStu(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case"/StudentServlet/checkStuOwnScore"  :
                try {
                    checkStuOwnScore(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

//            case "updatePW" :
//                try {
//                    updatePW(req,resp);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                break;

            case "/StudentServlet/checkpage"  :
                try {
                    checkpage(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "/StudentServlet/upload" :
                  upload(req,resp);
                  break;

            case "/StudentServlet/examing" :
                examing(req,resp);
                break;

        }
    }


    /**
     * 该Servlet处理业务是在StudentLogin.html页面下完成的,负责登录的信息验证
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {

        String number = req.getParameter("number");
        String password = req.getParameter("password");

        /**
         * 判断数据库中有无该学生的学号和密码，若能对应，则成功登录
         *
         */
        Student stu = new Student();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int flag = 0;
        try{
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(StudentSql.checkStuPWSql);
            ps.setInt(1,stu.getNumber());
            rs = ps.executeQuery();
            while(rs.next()){
                String temp = rs.getString("password");
                if (temp.equals(stu.getUsername())){
                    flag = 2; //用户名和密码都正确
                }else {
                    flag = 1; //密码错误
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.close(conn,ps,rs);
        }
       if(flag==2){
           req.setAttribute("msg","登录成功");
           resp.sendRedirect("student.html");
       }else {
           req.setAttribute("msg","密码错误，登录失败");
       }

    }


    /**
     * 该servle处理业务是在StudentRegister.html页面下完成的，负责将注册逻辑，并且将注册信息保存至数据库中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void register(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException,SQLException {

//        String jsonString = GSONUtil.getJson(req);     //得到json字符串
//        Gson gson = new Gson();         //创建一个gson对象
//        /**
//         * 用fromJson方法将jsonString解析，得到一个对象
//         */
//        Student stu = gson.fromJson(jsonString,Student.class);
//        HttpSession session = req.getSession();
//        stu = (Student)session.getAttribute("stu");

        Student stu = new Student();
        System.out.println(stu);

        /**
         * 接收客户端发来的各个参数
          */
         Integer number = Integer.parseInt(req.getParameter("number"));
         String username = req.getParameter("username");
         String password = req.getParameter("password");
         String repassword = req.getParameter("repassword");
         String college = req.getParameter("college");
         String major = req.getParameter("major");

        /**
         * 将用户注册的信息保存在session中
         */
        HttpSession session = req.getSession();  //获取session中的信息
        session.setAttribute("number",number);
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        session.setAttribute("college",college);
        session.setAttribute("major",major);

       if (password.equals(repassword)){

           stu.setNumber(number);
           stu.setUsername(username);
           stu.setCollege(college);
           stu.setMajor(major);
           stu.setPassword(password);
           System.out.println(stu);
           req.setAttribute("msg","注册成功");
           resp.sendRedirect("StudentLogin.html");
           AddStuServiceImpl assi = new AddStuServiceImpl();
           assi.addStudent(stu);
           System.out.println("注册成功");
       }else{
           System.out.println("注册失败");
           req.setAttribute("msg","两次密码不一致，注册失败");
       }

    }


    /**
     * 该servlet处理业务是在Student.html页面下完成的,负责查询学生个人信息
     */
    private void checkStu(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {

        String jsonString = GSONUtil.getJson(req);     //得到json字符串
        Gson gson = new Gson();         //创建一个gson对象
        Student stu = gson.fromJson(jsonString,Student.class);
        HttpSession session = req.getSession();
        stu = (Student)session.getAttribute("stu");

        CheckStuServiceImpl cssi = new CheckStuServiceImpl();
        List<Student> list = cssi.getStudent(); //得到了查询的一行学生个人信息
        for (Student student: list){
            System.out.println(student.toString());
        }
        resp.getWriter().write(list.toString());

    }


    /**
     * 该servlet处理业务是在Student.html页面下完成的，负责学生查询个人成绩
     */
    private void checkStuOwnScore(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException,SQLException{

        String jsonString = GSONUtil.getJson(req);     //得到json字符串
        Gson gson = new Gson();         //创建一个gson对象
        StudentScore ss = gson.fromJson(jsonString,StudentScore.class); //将其解析成对象
        HttpSession session = req.getSession();
        ss = (StudentScore) session.getAttribute("ss");  //将对象信息保存在session中

        CheckStuServiceImpl cssi = new CheckStuServiceImpl();
        List<StudentScore> list = cssi.getStudentOwnScore(); //得到了查询的一行学生成绩信息
        for (StudentScore studentScore: list){
            System.out.println(studentScore.toString());
        }
        resp.getWriter().write(list.toString());
    }


    /**
     * 该servlet处理业务是在student-information.html页面下完成的，负责学生密码的修改
     */
    // private void updatePW(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException,SQLException{ }


    /**
     * 该servlet处理业务是在student.html页面下完成的，处理查询考试试卷（分页查询）的请求,并返回信息
     */
     private void checkpage(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException,SQLException{
            List<ExamPage> exampages = new ArrayList<>();
            int pages = 0;
            int count = 0;
            int totalpages = 0;
            int limit = 4;
            Connection conn = C3P0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement(StudentSql.checkPageSql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);                     //总记录数
            }
            totalpages = (int)Math.ceil(count/(limit*1.0));  //总页数
            String strPage = req.getParameter("pages"); //获取当前页面数
            if (strPage != null){
                pages = -1;
            }else {
                try{
                    pages = Integer.parseInt(strPage);
                }catch (Exception e){
                    pages = -1;
                }

                if (pages < 1){
                    pages = 1;
                }
                if (pages > totalpages){
                    pages = totalpages;
                }
            }
            rs = ps.executeQuery("SELECT * FROM exampage ORDER BY p_score limit"+(pages-1)*limit+","+limit);

            while(rs.next()){     //遍历显示
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String time = rs.getString(3);
                String teacher = rs.getString(4);

                ExamPage examPage = new ExamPage(id,name,time,teacher);
                exampages.add(examPage);

            }
     }


    /**
     * 该servlet处理业务负责学生在考试页面中上传文件
     */
      private void upload(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
          req.setCharacterEncoding("utf-8");
          resp.setContentType("text/html,charset=utf-8");
          PrintWriter out = resp.getWriter();
          DiskFileItemFactory sf = new DiskFileItemFactory(); //实例化磁盘被文件列表工厂
          String path = req.getSession().getServletContext().getRealPath("/upload"); //得到上传文件的存放目录
          sf.setRepository(new File(path));  //设置文件存放目录
          sf.setSizeThreshold(1024*1024);    //设置文件上传小于1M放在内存中
          String rename = ""; //文件新生成的文件名
          String fileName = "";//文件原名
          String name = "";
          //从工厂得到servletupload文件上传类
          ServletFileUpload sfu = new ServletFileUpload(sf);

          try{
              List<FileItem> lst = sfu.parseRequest(req);
              for (FileItem fileItem:lst){
                  if(fileItem.isFormField()){
                      if ("name".equals(fileItem.getFieldName())){
                          name = fileItem.getString("utf-8");
                      }
                  }else {
                      //获得文件名称
                      fileName = fileItem.getName();
                      fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                      String houzhui = fileName.substring(fileName.lastIndexOf("."));
                      rename = UUID.randomUUID()+houzhui;
                      fileItem.write(new File(path,rename));
                  }
              }
          } catch (FileUploadException e) {
              e.printStackTrace();
          } catch (Exception e) {
              e.printStackTrace();
          }
          resp.sendRedirect("upload_success.html");
          out.flush();
          out.close();

      }


    /**
     * 在exam.html页面选择的某一套考试题examing.html下完成
     * 该servlet完成将考试的记录保存至数据库中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void examing(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{}


}
