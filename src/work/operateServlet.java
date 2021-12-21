package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/operateServlet")
public class operateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String sql= request.getParameter("sql");
        int operate=Integer.parseInt(request.getParameter("operate"));
        boolean a = false;
        try {
            a=JDBC.operate(sql,operate);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(a)
            System.out.println("操作成功");
        request.setAttribute("bool",a);
        request.getRequestDispatcher("/operate.jsp").forward(request,response);
    }

}
