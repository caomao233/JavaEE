<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Result</title>
    <meta charset="utf-8">
</head>
    <body>
    <table class="table">
	<div><h3>id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp; qq&nbsp;&nbsp;&nbsp;邮箱</h3></div>
    <%
    List<String> table = (List<String>)request.getAttribute("table");
    for (int i=0;i<(table.size());i=i+5) {
    %>
    <tr>
        <td><%=table.get(i)%></td>
        <td><%=table.get(i+1)%></td>
        <td><%=table.get(i+2)%></td>
        <td><%=table.get(i+3)%></td>
        <td><%=table.get(i+4)%></td>
        <td> <form action="${pageContext.request.contextPath}/getPhoto.jsp?uname=<%=table.get(i)%>" method = "post">

            <input type = "submit" value = "查看图片">

        </form>   </td>
    </tr>
    <%
    }%>
	</table>
	  <nav>
			<ul>
				<%
				int currentPage = (int) request.getAttribute("currentPage");
                int totalPage = (int) request.getAttribute("totalpage");
                int displayNumber = (int) request.getAttribute("number");
                if (totalPage != 1){
                	if(currentPage != 1){
                %>
                <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=currentPage-1%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post">上一页</a></li>
                 <%
                	}
                    for(int i = 1;i<=totalPage;i++){
                    	if(currentPage != i){
                        %>
                        <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=i%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post"><%=i%></a></li>
                        <%
                        
                    	}
                        if(currentPage == i){

                        %>
                        <li><a href="#"><%=i%></a></li>
                        <%
                         }
                    }
					if(currentPage < totalPage){
                      	%>
                        <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=currentPage+1%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post">下一页</a></li>
                        <%
                        
					}
           		}
                        %>
                    </ul>
		</nav>
    <a href="${pageContext.request.contextPath}/search.jsp" class="card-link">继续查询</a>
    </body>
</html>
