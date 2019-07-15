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
    PostModel um = new PostModel();
    um.LoadPost();
    ArrayList<Post> lst_posts = um.getPostList();
%>

<div class="container">
    <h2 class="table-title">List of post</h2>
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
                        <td>${post.getStatus()}</td>
                        <td>${user.getRating()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
