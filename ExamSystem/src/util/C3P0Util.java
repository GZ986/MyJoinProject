package util;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

public class C3P0Util  {
        static ComboPooledDataSource ds = new ComboPooledDataSource();

//        public static void configDataSource() {
//        try {
//            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/examsystem?characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=Asia/shanghai");
//            ds.setUser("root");
//            ds.setPassword("root");
//            ds.setInitialPoolSize(10);
//            ds.setMaxPoolSize(100);
//            ds.setMinPoolSize(10);
//            ds.setAcquireIncrement(3);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
  //  }

        public static Connection getConnection(){
            try{
                  System.out.println(ds.getConnection());
                  return ds.getConnection();
            }catch (Exception e){

                  return null;
            }
        }

        public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
                if (conn!=null && ps!=null && rs!=null){
                        try{
                             rs.close();
                             ps.close();
                             conn.close();

                        }catch (SQLException e){
                                e.printStackTrace();
                        }
                }

        }
}
