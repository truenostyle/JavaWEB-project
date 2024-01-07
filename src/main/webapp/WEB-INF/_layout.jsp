<%@ page import="java.util.Date" %>
<%@ page import="step.learning.User" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String pageBody = (String) request.getAttribute("page-body");
    String context = request.getContextPath(); // /JavaWeb201 - Dekploy - App context
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java web</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <%--    Site CSS--%>
    <link rel="stylesheet" href="<%=context%>/css/site.css?time=<%= new Date().getTime()%>"/>
    <%--    <script src="<%=context%>/js/site.js"></script>--%>
</head>
<body>




<nav>
    <div class="nav-wrapper ocean darken-4">


        <a href="<%= context %>/" class="left site-logo">AIRBNB</a>
        <%
            System.out.println("Session ID: " + session.getId());
            System.out.println("Session auth-status: " + session.getAttribute("auth-status"));
            User user = null;
            if(session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
        %>
        <div class="right" style="display: flex; align-items: center;">
            <p style="margin-right: 10px;"> <%= user.getEmail() %> </p>
            <a href="<%=context%>/admin-panel" class="btn waves-effect waves-light blue" style="margin-right: 10px;">Admin Panel</a>

            <a href="<%=context%>/logout" class="btn waves-effect waves-light red">Logout</a>
        </div>



        <%
        } else {
        %>
        <ul id="nav-me" class="right hide-on-med-and-down">
            <li <%="signup.jsp".equals(pageBody) ? "class='active'" : ""%>>
                <a href="<%=context%>/signup">Sign Up</a>
            </li>
            <li>
                <a class="modal-trigger" href="#loginModal">Log In</a>
            </li>
        </ul>
        <%
            }
        %>




    </div>
</nav>

<%--<%=String.format("%s/%s", context, pageBody)%>--%>
<div class="container main-content">
    <jsp:include page="<%= pageBody %>"/>
</div>


<footer class="page-footer footer ocean darken-4">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">AIRBNB</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer
                    content.</p>
            </div>

        </div>
    </div>
    <div class="footer-copyright ">
        <div class="container">
            © <span id="currentYear">ff</span> Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>


<!-- Modal Structure -->
<div id="loginModal" class="modal">
    <div class="modal-content">
        <h4>Войти</h4>
        <div class="row">
            <form action="auth" method="post" class="col s12" id="loginForm">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" name="email" class="validate" required>
                        <label for="email">Email</label>
                    </div>
                    <div class="input-field col s12">
                        <input id="password" type="password" name="password" class="validate" required>
                        <label for="password">Password</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">Entrance
                    <i class="material-icons right">send</i>
                </button>
            </form>
            <div id="loginError" style="color: red;">
                <%
                    if (session.getAttribute("auth-status") != null) { %>
                    <p>Wrong password or email</p>
                <% } else { %>
                    <p></p>
                <% }  %><!-- Используйте Expression Language для отображения сообщения об ошибке -->
            </div>
        </div>
    </div>
</div>

<%
    String openModal = (String) session.getAttribute("openModal");
    if("true".equals(openModal)) {
        session.removeAttribute("openModal");
%>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        let instance = M.Modal.getInstance(document.querySelector('#loginModal'));
        instance.open();
    });
</script>
<%
    }
    else {
        System.out.println("net");
    }
%>


<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        let elems = document.querySelectorAll('.modal');
        let instances = M.Modal.init(elems);
    });
</script>

</body>
</html>
