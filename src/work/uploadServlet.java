package work;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession session=(HttpSession)request.getSession();
        ServletContext application=(ServletContext)session.getServletContext();
        String uname= (String) application.getAttribute("uname");
        String path =request.getServletContext().getRealPath("./")+"/upload" ;
        String fileName = null;
        int type=0;
        if(isMultipart){

            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {
                List<FileItem> items=upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while(iter.hasNext()){
                    FileItem item = iter.next();
                    String follow="";
                    // 文件上传
                        if(item.getName().contains(".png")) {
                            follow = ".png";
                            type = 1;
                        }
                        else if (item.getName().contains(".jpg")) {
                            follow = ".jpg";
                            type=2;
                        }
                        fileName=uname+follow;
                        //String path=request.getSession().getServletContext().getRealPath("upload");
                        File file = new File(path,fileName);
                        item.write(file);


                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            JDBC.setPhoto(uname,type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

            request.getRequestDispatcher("/upload.jsp").forward(request,response);



    }


}
