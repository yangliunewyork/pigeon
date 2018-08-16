<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css" />" >
</head>
<body>

<h1>There are in total ${articles.size()} articles.</h1>


<c:if test="${not empty articles}">
    <c:forEach var="article" items="${articles}" >
        <tr>
            <td>Title: <c:out value="${article.title}"/></td>
            <td>Author: <c:out value="${article.author}"/></td>
            <td>Content: <c:out value="${article.content}"/></td>
        </tr>
    </c:forEach>
</c:if>
</body>
</html>
