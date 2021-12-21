package work;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet("/photosearch")
public class photosearch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession session=(HttpSession)request.getSession();
        ServletContext application=(ServletContext)session.getServletContext();
        String uname= (String) application.getAttribute("uname");
        String path =request.getServletContext().getRealPath("./")+"/forsearch" ;
        String fileName = null;
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
                    }
                    else if (item.getName().contains(".jpg")) {
                        follow = ".jpg";
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyHHmmss");
                    fileName=formatter.format(new Date())+follow;
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
        ArrayList<String> allid=new  ArrayList<String>();
        try {
            allid=JDBC.getallid();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> student=new ArrayList<String>();
        for(String id:allid){
            File file=new File(request.getServletContext().getRealPath("./")+"/upload/"+id+".jpg");
            System.out.println(id);
            if(file.exists()){
                if(FaceEngineTest.facejudge(request.getServletContext().getRealPath("./")+"/upload/"+id+".jpg",path+File.separator+fileName))
                {
                    System.out.println(id);
                    try {
                       studentData s=JDBC.selectid(id);
                       student.add(s.getId());
                       student.add(s.getName());
                       student.add(s.getPhone());
                       student.add(s.getQq());
                       student.add(s.getEmail());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(student!=null) {
            for(String s:student)
                System.out.println(s);
            request.setAttribute("result", student);
            request.getRequestDispatcher("/photoresult.jsp").forward(request,response);
        }
        else
            request.getRequestDispatcher("/no_result.jsp").forward(request,response);



    }
}
