<%-- 
    Document   : logout
    Created on : Jun 28, 2019, 8:14:43 AM
    Author     : KhanhVH@fe.edu.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("username") != null) {
    session.removeAttribute("username");
}
response.sendRedirect("index.jsp");
%>