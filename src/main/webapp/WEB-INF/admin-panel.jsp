<%@ page import="step.learning.User" %><%--
  Created by IntelliJ IDEA.
  User: jigsaw
  Date: 07.01.2024
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
<%
    User user = null;
    if(session.getAttribute("user") != null) {
        user = (User) session.getAttribute("user");
%>

<div class="container">
    <h2>Add New Appartament</h2>
    <form action="create" method="POST"> <!-- Замените yourServletURL на URL вашего сервлета для обработки формы -->
        <div class="input-field">
            <input id="name" type="text" name="name" class="validate" required>
            <label for="name">Name</label>
        </div>

        <div class="input-field">
            <input id="city" type="text" name="city" class="validate" required>
            <label for="city">City</label>
        </div>

        <div class="input-field">
            <input id="price" type="number" name="price" class="validate" required>
            <label for="price">Price</label>
        </div>

        <div class="input-field">
            <input id="image" type="text" name="image" class="validate" required>
            <label for="image">Image URL</label>
        </div>

        <div class="input-field">
            <textarea id="description" name="description" class="materialize-textarea" required></textarea>
            <label for="description">Description</label>
        </div>

        <button class="btn waves-effect waves-light" type="submit" name="action">Add Appartament
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>


<div class="container">
    <h1>Delete Appartament</h1>
    <form action="delete" method="post">
        <div class="input-field">
            <input id="delName" type="text" name="name" class="validate" required>
            <label for="delName">Name</label>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Delete Appartament
            <i class="material-icons right">send</i>
        </button>
    </form>

</div>
<%
} else {
%>
    <h1>You must be logged in to access this page</h1>
<%
    }
%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
