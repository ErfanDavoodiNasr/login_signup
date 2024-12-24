<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #006400, #8B0000);
            color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            overflow: hidden;
            text-align: center;
        }

        header {
            position: absolute;
            top: 20px;
            width: 90%;
            background: rgba(0, 0, 0, 0.7);
            padding: 10px 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
        }

        header h1 {
            font-size: 32px;
            margin: 0;
            font-weight: bold;
            color: #32CD32;
        }

        .hero {
            margin-top: 120px;
        }

        .hero h2 {
            font-size: 50px;
            margin: 0 0 20px;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
            color: #FFD700;
        }

        .hero p {
            font-size: 20px;
            margin-bottom: 40px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
        }

        .info {
            font-size: 22px;
            margin-bottom: 20px;
            font-weight: 500;
            background: rgba(255, 255, 255, 0.1);
            padding: 15px 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            color: #fff;
        }

        .hero button {
            padding: 15px 30px;
            font-size: 18px;
            font-weight: bold;
            background: #8B0000;
            color: #fff;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            transition: all 0.3s;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }

        .hero button:hover {
            background: #006400;
            transform: scale(1.1);
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome <%= session.getAttribute("firstName") %></h1>
</header>

<div class="hero">
    <div class="info">
        <p>ID: <%= session.getAttribute("id") %></p>
        <p>First Name: <%= session.getAttribute("firstName") %></p>
        <p>Last Name: <%= session.getAttribute("lastName") %></p>
        <p>Phone: <%= session.getAttribute("phoneNumber") %></p>
    </div>
</div>

<script>
    <%
        if (request.getAttribute("message") != null) {
    %>
    const messages = '<%= request.getAttribute("message") %>';
    alert(messages);
    <% } %>
</script>

</body>
</html>