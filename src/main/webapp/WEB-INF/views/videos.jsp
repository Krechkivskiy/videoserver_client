<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Spring Web MVC project</title>
</head>
<body>
    <h3>your videos</h3>
    <c:if test="${not empty videos}">
        <c:forEach items="${videos}" var="video">
       <a href="/video?name=${video.name}", method="get">  ${video.name} </a>
    </c:forEach>
    </c:if>
</body>
</html>
