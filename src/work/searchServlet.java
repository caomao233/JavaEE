package work;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;
import java.nio.*;
/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");
         String[] str =new String[10];
         ArrayList<studentData> itemList=new ArrayList<studentData>();

         str[0] = request.getParameter("id");
         str[1] = request.getParameter("name");
         str[2] = request.getParameter("phone");
         str[3] = request.getParameter("qq");
         str[4] = request.getParameter("email");
         String nu=request.getParameter("number");
         int num=5;
         if(!"".equals(nu)) {
              num = Integer.parseInt(nu);
         }
         int i=0;
         for(i=0;i<5;i++){
             if(!str[i].equals("")){
                 break;
             }
         }

         String kind=null;
         switch(i) {
             case 0: kind="id";break;
             case 1: kind="name";break;
             case 2: kind="phone";break;
             case 3: kind="qq";break;
             case 4: kind="email";break;
         }

         try {
             itemList=JDBC.select(str[i],kind);
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         HttpSession session=(HttpSession)request.getSession();
         ServletContext application=(ServletContext)session.getServletContext();

         int totalpage = itemList.size()/num;

         if(itemList.size()%num!=0) {
             totalpage++;
         }
         
         if(itemList!=null) {
        	 application.setAttribute("result", itemList);
         }


         application.setAttribute("flag",1);
         application.setAttribute("number",num);
         application.setAttribute("totalpage",totalpage);
         response.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");
         request.getRequestDispatcher("/resultDispose").forward(request,response);
	}

}
