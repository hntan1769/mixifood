<%-- 
    Document   : login_process
    Created on : March 10, 2019, 10:25:04 AM
    Author     : KhanhVH@fe.edu.vn
--%>

<%
String username = request.getParameter("txtUsername");
String password = request.getParameter("txtPassword");

if (username.equals(password)) {
    session.setAttribute("username", username); 
} else if(session.getAttribute("username") != null) {
    session.removeAttribute("username");
}
response.sendRedirect("admin.jsp");
%>