<%--
  Created by IntelliJ IDEA.
  User: jigsaw
  Date: 04.01.2024
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <!-- Materialize CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<div class="container">
    <h2>Registration</h2>
    <form action="register" method="post">
        <div class="input-field">
            <input id="first_name" type="text" name="firstName" class="validate" required>
            <label for="first_name">First Name</label>
        </div>
        <div class="input-field">
            <input id="last_name" type="text" name="lastName" class="validate" required>
            <label for="last_name">Last Name</label>
        </div>
        <div class="input-field">
            <input id="phone" type="tel" name="phone" class="validate" required>
            <label for="phone">Phone Number</label>
        </div>
        <div class="input-field">
            <input id="email" type="email" name="email" class="validate" required>
            <label for="email">Email</label>
        </div>
        <div class="input-field">
            <input id="password" type="password" name="password" class="validate" required>
            <label for="password">Password</label>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Next
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>

<!-- Materialize JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
