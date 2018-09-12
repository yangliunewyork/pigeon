<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
                <form:input path="content"  type="hidden" class="form-control" />
                <form:input path="quillContent"  type="hidden" class="form-control" />
                <div id="editor-container" name="editor-container"></div>
            </div>
            <div class="row">
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form:form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
    var toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
        ['blockquote', 'code-block'],

        [{ 'header': 1 }, { 'header': 2 }],               // custom button values
        [{ 'list': 'ordered'}, { 'list': 'bullet' }],
        [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
        [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
        [{ 'direction': 'rtl' }],                         // text direction
        [ 'link', 'image', 'video', 'formula' ],          // add's image support
        [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

        [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
        [{ 'font': [] }],
        [{ 'align': [] }],

        ['clean']                                         // remove formatting button
    ];
    var quill = new Quill('#editor-container', {
        modules: {
            toolbar: toolbarOptions
        },
        placeholder: 'Compose an epic...',
        theme: 'snow'
    });


    // When user click Submit button,
    // we populate the form:content with Quill content
    var form = document.querySelector('form');
    form.onsubmit = function () {
        // Populate hidden form on submit
        var quillContent = document.querySelector('input[name=quillContent]');
        quillContent.value = JSON.stringify(quill.getContents());

        var textContent = document.querySelector('input[name=content]');
        textContent.value = JSON.stringify(quill.getText());

        console.log(textContent.value);

        //console.log("Submitted", $(form).serialize(), $(form).serializeArray());
    };


</script>

</body>
</html>
