<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Spring Web MVC project</title>
</head>
<h1>Index page</h1>
<body>
    <video width="1000" height="400" autoplay="autoplay" controls >
        <source src="/video?name=${name}" type="video/mp4" >
    </video>
</body>
</html>