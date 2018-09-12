<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css" />" >
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
</head>
<body>


<h3> Title : ${article.title} </h3>
<div id="articleContent" style="display:none;"> ${article.quillContent}</div>
<div id="editor-container"></div>
<h3> Author : ${article.author} </h3>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function initializeQuillContent() {
    // Create a readonly Quill editor
    var quill = new Quill('#editor-container', {
        modules: {
            toolbar: [
                ['bold', 'italic'],
                ['link', 'blockquote', 'code-block', 'image'],
                [{ list: 'ordered' }, { list: 'bullet' }]
            ]
        },
        readOnly: true,
        theme: 'snow' // or 'bubble'
    });
    // Get Delta value from ${article.content}
    // and use it to populate the Quill editor's content
    var content = JSON.parse(document.getElementById('articleContent').innerHTML);
    quill.setContents(content);
}
$( document ).ready(function() {
    initializeQuillContent();
});
</script>
</html>