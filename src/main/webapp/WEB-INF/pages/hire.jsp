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
    <%@ include file="/WEB-INF/pages/components/header.jsp" %>
</head>
<body>
<div class="bg-white rounded px-4 pt-3">
    <h2 class="mb-2 title">GenerateToken</h2>
    <form:form modelAttribute="registerToken" method="post" action="/hr/getToken">
        <div class="form-group">
            <form:label path="email">Email</form:label>
            <form:input type="email" path="email" class="form-control" placeholder="Enter Email"/>
            <form:errors path="email" class="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="houseID">houseID</form:label>
            <form:input type="number" path="houseID" class="form-control" placeholder="Enter House ID"/>
            <form:errors path="houseID" class="error-message"/>
        </div>
        <button type="submit" class="btn btn-outline-primary">Generate</button>
    </form:form>

<%--    <form method="POST" action="/hr/getToken">--%>
<%--    <div class="form-group">--%>
<%--        <label for="exampleInputEmail1">Email address</label>--%>
<%--        <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"--%>
<%--               placeholder="Enter email">--%>
<%--        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>

<%--        <label for="exampleInputHouseID">Email address</label>--%>
<%--        <input name="houseID" type="number" class="form-control" id="exampleInputHouseID" aria-describedby="emailHelp"--%>
<%--               placeholder="Enter email">--%>

<%--    </div>--%>
<%--    <button type="submit" class="btn btn-primary">GenerateToken</button>--%>
<%--    </form>--%>
</div>

</body>
</html>
