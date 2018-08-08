<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Article</h1>
    <form:form action="saveArticle" method="post" modelAttribute="article">
        <table>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>Content:</td>
                <td><form:input path="content" /></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><form:input path="author" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
