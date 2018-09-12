<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css" />" >
</head>
<body>

<h1>There are in total ${articles.size()} articles.</h1>



<c:if test="${not empty articles}">
    <table>
        <tr>
            <th> Title </th>
            <th> Author </th>
            <th>  </th>
        </tr>
        <c:forEach var="article" items="${articles}" >
            <tr id = "${article.id}">
                <td><c:out value="${article.title}"/></td>
                <td><c:out value="${article.author}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/articles/${article.id}">view</a>
                    <a href='javascript:void(0);' onclick='remove("${article.id}");'>remove</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    function remove(articleId){
        console.log(articleId);
        $.ajax({
            url: location.origin + "/articles/" + articleId,
            type: 'DELETE',
            contentType:'application/json',
            success: function(result){
                alert('success' + result);
                $(articleId).remove();
            },
            error: function(req, err){
                console.log(location.origin + "/articles/" + articleId);
                console.log('error' + err);
            }
        });
    }
</script>
</html>
