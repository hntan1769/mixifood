<%-- 
    Document   : index.jsp
    Created on : Jun 28, 2019, 7:59:59 AM
    Author     : KhanhVH@fe.edu.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%if(session.getAttribute("username") == null){%>
    <jsp:include page="login.html"/>
<%}else{%>
    <jsp:include page="admin.jsp"/>
<%}%>








