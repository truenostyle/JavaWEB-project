<%@ page import="step.learning.dao.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@ page import="step.learning.User"%>
<%@ page import="java.util.List" %>
<%@ page import="step.learning.Appartament" %>

<%  List<Appartament> appartaments = (List<Appartament>) request.getAttribute("appartaments");
    System.out.println("apparts: " + appartaments);

%>
<!DOCTYPE html>
<html>
<head>
    <title>AIRBNB</title>
</head>
<body>
<div class="container">
    <div class="row">
        <% if(appartaments != null) {
            for(Appartament appartament : appartaments) { %>
        <div class="col s4">
            <div class="card" style="height: 400px; position: relative;">
                <div class="card-image" style="height: 200px; overflow: hidden;">
                    <img src="<%=appartament.getImage()%>" style="width: 100%; object-fit: cover;">
                </div>
                <div class="card-content" style="overflow: hidden; height: 200px;">
                    <span class="card-title" style="font-size: 1.2em;"><%=appartament.getName()%></span>
                    <p style="font-size: 0.9em;"><%=appartament.getDescription()%></p>
                    <p style="position: absolute; bottom: 10%"><%=appartament.getCity()%></p>
                </div>
                <div style="position: absolute; bottom: 0; left: 9%; width: 80%;">
                    <div class="row" style="margin-bottom: 0;">
                        <div class="col s6 left-align">
                            <p><%=appartament.getPrice()%>$ night</p>
                        </div>
                        <div class="col s6 right-align">
                            <p>Rating: <%=appartament.getRating()%></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%  }
        } else { %>
        <p>No appartaments found.</p>
        <% } %>
    </div>
</div>

</body>
</html>
