package servlet;

import entity.ExamPage;
import entity.ExamPaper;
import service.impl.AMPageServiceImpl;
import service.impl.AMPaperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");
        PrintWriter out = resp.getWriter();
        String uri = req.getRequestURI();
        System.out.println(uri);
        System.out.println(req.getRequestURL());

        switch(uri) {
            case "/TeacherServlet/login":
                login(req, resp);
                break;

            case "/TeacherServlet/checkPage":
                try {
                    checkPage(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "/TeacherServlet/addPage":
                try {
                    addPage(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "/TeacherServlet/deletePage":
                try {
                    deletePage(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "/TeacherServlet/addPaper" :
                try {
                    addPaper(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "/TeacherServlet/deletePaper" :
                try {
                    deletePaper(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "/TeacherServlet/updatePaper":
                try {
                    updatePage(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


        }

    }


    private void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

        String number = req.getParameter("number");
        String password = req.getParameter("password");

        if ("join".equals(number) && "join666".equals(password)){

            Cookie cookie1 = new Cookie("username",number);
            Cookie cookie2 = new Cookie("password",password);
            cookie1.setMaxAge(7*24*60*60);
            cookie2.setMaxAge(7*14*60*60);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
            resp.sendRedirect("teacher.html");
        }else{
            resp.sendRedirect("teacher-login.html");
        }

    }

    private void checkPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, IOException {
        AMPageServiceImpl aPageSi = new AMPageServiceImpl();
        List<ExamPage> examPages = aPageSi.getExamPage();
        if (examPages!=null){
            req.setAttribute("examPages",examPages);
            req.setAttribute("msg","查询试卷成功");
        }else{
            resp.getWriter().write("查询失败");
        }

    }

    private void addPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        AMPageServiceImpl aPageSi = new AMPageServiceImpl();

        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String teacher = req.getParameter("teacher");

        ExamPage page = new ExamPage(id,name,time,teacher);
        int rows = aPageSi.addPage(page);
        if (rows>0){
            req.setAttribute("rows",rows);
            req.setAttribute("msg","添加试卷成功");
        }else {
            resp.getWriter().write("添加失败");
        }
    }

    private void deletePage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        AMPageServiceImpl aPageSi = new AMPageServiceImpl();
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String teacher = req.getParameter("teacher");

        ExamPage page = new ExamPage(id,name,time,teacher);
        int rows = aPageSi.deletePage(page);
        if (rows>0){
            req.setAttribute("rows",rows);
            req.setAttribute("msg","添加试卷成功");
        }else {
            resp.getWriter().write("添加失败");
        }

    }

    private void addPaper(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        AMPaperServiceImpl aPaperSi = new AMPaperServiceImpl();

        Integer qid = Integer.parseInt(req.getParameter("qid"));
        String type = req.getParameter("type");
        String content = req.getParameter("content");
        Integer value = Integer.parseInt(req.getParameter("value"));
        ExamPaper paper = new ExamPaper(qid,type,content,value);

        int rows = aPaperSi.addPaper(paper);
        if (rows>0){
            req.setAttribute("rows",rows);
            req.setAttribute("msg","添加试题成功");
        }else {
            resp.getWriter().write("添加失败");
        }
    }

    private void deletePaper(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        AMPaperServiceImpl aPaperSi = new AMPaperServiceImpl();
        Integer qid = Integer.parseInt(req.getParameter("qid"));
        String type = req.getParameter("type");
        String content = req.getParameter("content");
        Integer value = Integer.parseInt(req.getParameter("value"));
        ExamPaper paper = new ExamPaper(qid,type,content,value);
        int rows = aPaperSi.deletePaper(paper);
        if (rows>0){
            req.setAttribute("rows",rows);
            req.setAttribute("msg","添加试题成功");
        }else {
            resp.getWriter().write("添加失败");
        }

    }

    private void updatePage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        AMPaperServiceImpl aPaperSi = new AMPaperServiceImpl();
        Integer qid = Integer.parseInt(req.getParameter("qid"));
        String type = req.getParameter("type");
        String content = req.getParameter("content");
        Integer value = Integer.parseInt(req.getParameter("value"));
        ExamPaper paper = new ExamPaper(qid,type,content,value);
        int rows = aPaperSi.updatePaper(paper);
        if (rows>0){
            req.setAttribute("rows",rows);
            req.setAttribute("msg","添加试题成功");
        }else {
            resp.getWriter().write("添加失败");
        }
    }


}
