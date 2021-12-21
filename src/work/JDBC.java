package work;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBC {
    public static final String URL = "jdbc:mysql://localhost:3306/j2ee?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static ArrayList<studentData> select(String data, String kind) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql = "SELECT * FROM student where( " + kind + " like '%" + data + "%' )";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        //如果有数据，rs.next()返回true
        ArrayList<studentData> itemList = new ArrayList<studentData>();
        while (rs.next()) {
            studentData tempItem = new studentData();
            tempItem.setId(rs.getString("id"));
            tempItem.setName(rs.getString("name"));
            tempItem.setPhone(rs.getString("phone"));
            tempItem.setQq(rs.getString("qq"));
            tempItem.setEmail(rs.getString("email"));
            itemList.add(tempItem);
        }

//        for(int y=0;y<itemList.size();y++)
//        {
//            System.out.println(itemList.get(y).getName());
//        }


        pstmt.close();
        conn.close();
        return itemList;
    }
    public static studentData selectid(String id) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql = "SELECT * FROM student where id ="+id;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        studentData tempItem = new studentData();
        //如果有数据，rs.next()返回true
        while (rs.next()) {
            tempItem.setId(rs.getString("id"));
            tempItem.setName(rs.getString("name"));
            tempItem.setPhone(rs.getString("phone"));
            tempItem.setQq(rs.getString("qq"));
            tempItem.setEmail(rs.getString("email"));
        }

        pstmt.close();
        conn.close();
        return tempItem;
    }

    public static int login(String id, String password) throws ClassNotFoundException, SQLException {

        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql = "SELECT * from login where id = " + "'" + id + "'";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        if (rs.next()) {
            String pwd = rs.getString("password");
            int type =rs.getInt("type");
            if (pwd.equals(password)) {
                pstmt.close();
                conn.close();
                return type;
            } else {
                pstmt.close();
                conn.close();
                return -1;
            }
        }
        pstmt.close();
        conn.close();
        return -1;
    }

    public static boolean operate(String s,int o) throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql;
        if(o==1) {
            sql="insert into student values ("+s+",null)";
        }
        else if(o==2){
            sql="update student set "+s;
        }
        else if(o==3){
            sql="delete from student where id = '"+s+"'";
        }
        else
            return false;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int i = pstmt.executeUpdate(sql);
        pstmt.close();
        conn.close();
        if(i>0)
            return true;
        else
            return false;
    }

    public static boolean setPhoto(String uname,int type) throws ClassNotFoundException, SQLException, FileNotFoundException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql="update student set photo ="+type+" where id = '"+uname+"'";
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(sql);
        int num = pstmt.executeUpdate();
        if (num > 0) {
            System.out.println("图片插入成功！");
            pstmt.close();
            conn.close();
            return true;
        } else {
            System.out.println("图片插入失败！");
            pstmt.close();
            conn.close();
            return false;
        }

    }
    public static int getPhoto(String uname) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql = "select photo from student where id = '" + uname + "'";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        if (rs.next()) {
            int photo = rs.getInt("photo");
            return photo;//0为无图片，1为png格式，2为jpg格式
        }
        return 0;
    }
    public static ArrayList<String> getallid()throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        String sql = "SELECT * FROM student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        ArrayList<String> list = new ArrayList<String>();
        while (rs.next()) {
            String id=rs.getString("id");
            list.add(id);
        }
        pstmt.close();
        conn.close();
        return list;
    }
}
