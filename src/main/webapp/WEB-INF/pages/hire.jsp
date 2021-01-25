<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 1/23/21
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hire</title>
</head>
<body>
<div class="bg-white rounded px-4 pt-3">
    <h2 class="mb-2 title">GenerateToken</h2>
    <form method="POST" action="/hr/getToken">
    <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
               placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <button type="submit" class="btn btn-primary">GenerateToken</button>
    </form>
</div>

</body>
</html>
