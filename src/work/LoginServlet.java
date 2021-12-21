package work;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//控制器层：接受view层请求，并分发给Model层
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理登录请求
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        try {
            int type=JDBC.login(uname,upwd);
            if(type>=0) { //登录成功   type=0表示学生，=1表示管理员 =-1表示登录失败
                HttpSession session=(HttpSession)request.getSession();
                ServletContext application=(ServletContext)session.getServletContext();
                application.setAttribute("type",type);
                application.setAttribute("uname",uname);
                request.getRequestDispatcher("/search.jsp").forward(request, response);
            }else {//登录失败
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
