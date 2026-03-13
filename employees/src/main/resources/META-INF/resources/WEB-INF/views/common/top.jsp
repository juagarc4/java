<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<c:set var="urlBase">
    <c:url value="${application.contextPath}/"/>
</c:set>
<c:set var="urlEmployees">
    <c:url value="${application.contextPath}/employees"/>
</c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>Employee System</title>
</head>
<body class="d-flex flex-column min-vh-100">