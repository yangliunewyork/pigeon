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

<h1>Here is the article</h1>

<h3> Title : ${article.title} </h3>
<h3> Content : ${article.content} </h3>
<h3> Author : ${article.author} </h3>
</body>
</html>