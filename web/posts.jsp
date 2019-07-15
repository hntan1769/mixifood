<%-- 
    Document   : post_list
    Created on : Jul 12, 2019, 11:05:29 AM
    Author     : Macbook Air
--%>

<%@page import="mixifood.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*"%>
<%@page import="java.util.ArrayList"%>

<%    
    PostModel am = new PostModel();
    ArrayList<Post> lst_posts = null;

    int post_pageNum = 1;
    if (request.getParameter("p") != null) {
        post_pageNum = Integer.parseInt(request.getParameter("p"));
    }

    String post_query = "";
    if (request.getParameter("q") != null) {
        post_query = request.getParameter("q");
    }

    int post_rowPerPage = 25;
    if (session.getAttribute("post_rowPerPage") == null) {
        session.setAttribute("post_rowPerPage", 25);
    }
    post_rowPerPage = Integer.parseInt("" + session.getAttribute("post_rowPerPage"));

    int post_totalPages = 0;
    int post_visiblePages = 10;
    int post_visiblePages_2 = post_visiblePages / 2;
    
    String url = "?f=lst_posts";
    if (post_query != "") {
        url += "&q=" + post_query;
    }
    url += "&p=";
    
    try {
        am.search(post_query);
        
        post_totalPages = am.getTotalPages(post_rowPerPage);

        if (post_pageNum < 1) {
            post_pageNum = 1;
        } else if (post_pageNum > post_totalPages) {
            post_pageNum = post_totalPages;
        }
//    out.print("p: " + post_pageNum + " # row per page: " + post_rowPerPage + "<br/>");
        lst_posts = am.getPage(post_pageNum, post_rowPerPage);
    } catch (Exception e) {
        out.print(e);
    }
%>
<div class="container">
    <h2 class="table-title">List of Post</h2>

    <ul class="pagination pagination-sm" style="justify-content: center"> 
        <li class="page-item"><a class="page-link" href="<%=url%>1">First</a></li>
        <li class="page-item"><a class="page-link" href="<%=url + Math.max(1, post_pageNum - 1)%>">Previous</a></li>
            <%
                int post_minPage = Math.max(1, post_pageNum - post_visiblePages_2);
                int post_maxPage = Math.min(post_totalPages, post_pageNum + post_visiblePages_2);
                String post_active = "";
                for (int post_i = post_minPage; post_i <= post_maxPage; ++post_i) {
                    post_active = post_i == post_pageNum ? " active" : "";
            %>
        <li class="page-item<%=post_active%>">
            <a class="page-link" href="<%=url + post_i%>"><%=post_i%></a>
        </li>
        <%  }%>
        <li class="page-item"><a class="page-link" href="<%=url + Math.min(post_totalPages, post_pageNum + 1)%>">Next</a></li>
        <li class="page-item"><a class="page-link" href="<%=url + post_totalPages%>">Last</a></li>
    </ul>

    <form id="post_search" action="">
    <div class="row">
        <div class="input-group col-md-12">
            <input type="hidden" name="f" value="lst_posts" />
            <input class="form-control py-2 border-right-0 border" type="search"  
                   id="example-search-input" name="q" placeholder="Search" />
            <input type="hidden" name="p" value="1" />
            <span class="input-group-append">
                <button class="btn btn-outline-secondary border-left-0 border" type="submit">
                    <i class="fa fa-search"></i>
                </button>
            </span>
        </div>
    </div>
    </form>

    <div class="table-responsive">
        <style type="text/css">
            table{
                border: 1px solid black;
            }
            td, tr, th{
                border: 1px solid black;
            }
            td h1 {
                font-size: 14px;
            }
            td h1 strong{
                font-size: 14px;
                font-weight: normal;
            }
            
        </style>
        <table class="table table-sm table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th>Food name</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Rating</th>
                    <th>
                        <button class="btn btn-success btn-sm"><i class="fa fa-plus-square"></i></button>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="<%=lst_posts%>" var="post">
                    <tr>  
                        <td>${post.getPost_id()}</td>
                        <td>${post.getTitle()}</td>
                        <td>${post.getFood_name()}</td>
                        <td>${post.getPrice()}</td>
                        <td class="text-center ${post.getStatus()==0? 'text-success': 'text-danger'}">
                            <i class="fa fa-globe"></i>
                        </td>
                        <td>${user.getRating()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
  