<%-- 
    Document   : posts
    Created on : Jul 12, 2019, 9:48:54 AM
    Author     : Macbook Air
--%>

<%@page import="mixifood.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*"%>
<%@page import="java.util.ArrayList"%>

<%


%>

<%    UserModel um = new UserModel();
    um.LoadUser();
    ArrayList<User> lst_users = null;

    int user_pageNum = 1;
    if (request.getParameter("p") != null) {
        user_pageNum = Integer.parseInt(request.getParameter("p"));
    }
    String user_query = "";
    if (request.getParameter("q") != null) {
        user_query = request.getParameter("q");
    }

    int user_rowPerPage = 5;
    session.setAttribute("user_rowPerPage", 5);
    if (session.getAttribute("user_rowPerPage") == null) {
        session.setAttribute("user_rowPerPage", 5);
    }
    user_rowPerPage = Integer.parseInt("" + session.getAttribute("user_rowPerPage"));

    int user_totalPages = 0;
    int user_visiblePages = 7;
    int user_visiblePages_2 = user_visiblePages / 2;

    String url = "?f=users";
    if (user_query != "") {
        url += "&q=" + user_query;
    }
    url += "&p=";

    try {
        um.search(user_query);

        user_totalPages = um.getTotalPages(user_rowPerPage);

        if (user_pageNum < 1) {
            user_pageNum = 1;
        } else if (user_pageNum > user_totalPages) {
            user_pageNum = user_totalPages;
        }
//    out.print("p: " + user_pageNum + " # row per page: " + user_rowPerPage + "<br/>");
        lst_users = um.getPage(user_pageNum, user_rowPerPage);
    } catch (Exception e) {
        out.print(e);
    }
%>

<div class="container">
    <h2 class="table-title">List of post</h2>
    <div class="table-responsive">
        <style type="text/css">


            td h1 {
                font-size: 14px;
            }
            td h1 strong{
                font-size: 14px;
                font-weight: normal;
            }
            thead{
                background-color: #cc9933;
                color: white;
            }

        </style>
        <h2 class="table-title">List of Account</h2>

        <ul class="pagination pagination-sm" style="justify-content: center"> 
            <li class="page-item"><a class="page-link" href="<%=url%>1">First</a></li>
            <li class="page-item"><a class="page-link" href="<%=url + Math.max(1, user_pageNum - 1)%>">Previous</a></li>
                <%
                    int user_minPage = Math.max(1, user_pageNum - user_visiblePages_2);
                    int user_maxPage = Math.min(user_totalPages, user_pageNum + user_visiblePages_2);
                    String user_active = "";

                    for (int user_i = user_minPage; user_i <= user_maxPage; ++user_i) {
                        user_active = user_i == user_pageNum ? " active" : "";
                %>
            <li class="page-item<%=user_active%>">
                <a class="page-link" href="<%=url + user_i%>"><%=user_i%></a>
            </li>
            <%  }%>
            <li class="page-item"><a class="page-link" href="<%=url + Math.min(user_totalPages, user_pageNum + 1)%>">Next</a></li>
            <li class="page-item"><a class="page-link" href="<%=url + user_totalPages%>">Last</a></li>
        </ul>

        <form class="form-horizontal" id="user_search" action="">
<!--            <div class="">
                <div class="input-group col-md-6">
                    <input type="hidden" name="f" value="users" />
                    <input class="form-control py-2 border-right-0 border" type="search"  
                           id="example-search-input" name="q" placeholder="Search" />
                    <span class="input-group-append">
                        <button class="btn btn-outline-secondary border-left-0 border" type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                    <input type="hidden" name="p" value="1" />

                </div>

            </div>-->

            <div class="input-group md-form form-sm pl-0">
                <input class="form-control my-0 py-1 amber-border" type="text" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                  <span class="input-group-text amber lighten-3" id="basic-text1"><i class="fa fa-search text-grey"
                      aria-hidden="true"></i></span>
                </div>
              </div>
        </form>
        <table class="table table-sm table-striped table-hover">
            <thead class="black white-text table-responsive">
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Status</th>
                    <th>Admin</th>
                    <th>
                        <button class="btn btn-success btn-sm"><i class="fa fa-plus-square"></i></button>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="<%=lst_users%>" var="user">
                    <tr>  
                        <td>${user.getUser_id()}</td>
                        <td>${user.getUser_fullname()}</td>
                        <td>${user.getUser_username()}</td>
                        <td>${user.getUser_phone()}</td>
                        <td>${user.getUser_email()}</td>
                        <td>${user.getUser_status()}</td>
                        <td>${user.getIsAdmin()}</td>
                        <td>${user.getIsAdmin()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
