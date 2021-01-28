<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/components/header.jsp" %>
    <title>Update Book Form</title>
    <link rel="stylesheet" href="/css/register.css?ver=<?php echo rand(111,999)?>" />
</head>
<body>
<div class="container py-4">
    <div class="bg-white rounded px-4 py-3">
        <h2 class="mb-2 title">REGISTER</h2>

        <form:form modelAttribute="registerUser" method="post" action="/auth/register">
            <div class="form-group">
                <form:label path="userName">Name</form:label>
                <form:input type="text" path="userName" class="form-control" placeholder="Enter name"/>
                <form:errors path="userName" class="error-message"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password</form:label>
                <form:input type="text" path="password" class="form-control" placeholder="Enter password"/>
                <form:errors path="password" class="error-message"/>
            </div>
            <div class="form-group">
                <form:label path="email">Email</form:label>
                <form:input readonly="true" type="email" path="email" class="form-control"/>
            </div>
            <div class="form-group">
                <form:label path="houseId">Your Assigned House Id</form:label>
                <form:input readonly="true" type="value" path="houseId" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-outline-primary">Register</button>
        </form:form>

        <h3 class="token-message my-3">${tokenErrorMessage}</h3>
    </div>
</div>
</body>
</html>