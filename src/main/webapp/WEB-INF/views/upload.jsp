
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
Select a file to upload: <br />
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="5000000" />
    <input type="checkbox" name="open" value="open">Access another people watch this video<Br>    <br />
    <input type="submit" value="Upload File" />
</form>
</body>
</html>