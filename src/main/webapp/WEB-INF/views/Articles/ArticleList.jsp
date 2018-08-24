<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html xmlns:th="http://www.thymeleaf.org">
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
            <td>
                <a href="${pageContext.request.contextPath}/articles/${article.id}">view</a>
            </td>
            <td>
                <a href='javascript:void(0);' onclick='remove(${article.id});'>remove</a>
            </td>
        </tr>
    </c:forEach>
</c:if>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

    function remove(articleId){
        $.ajax({
            url:"articles/"+articleId,
            type: 'DELETE'
        });
    }
</script>
</html>
