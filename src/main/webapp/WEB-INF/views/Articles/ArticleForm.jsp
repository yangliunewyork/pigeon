<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
</head>
<body>
<div align="center">

    <div id="form-container" class="container">
        <form:form action="add" method="post" modelAttribute="article">
            <div class="row">
                <div class="form-group">
                    <label>Title</label><form:input path="title" />
                </div>
            </div>
            <div class="row form-group">
                <label >content</label>
                <form:input path="content" />
                <div id="editor-container">
                    <p> Content.... </p>
                </div>
            </div>
            <div class="row">
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form:form>
    </div>
</div>

<script>
var quill = new Quill('#editor-container', {
    modules: {
        toolbar: [
            ['bold', 'italic'],
            ['link', 'blockquote', 'code-block', 'image'],
            [{ list: 'ordered' }, { list: 'bullet' }]
        ]
    },
    placeholder: 'Compose an epic...',
    theme: 'snow'
});
</script>

</body>
</html>
