<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to bottom right, #6a11cb, #2575fc);
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #fff;
        }

        .form-container {
            background: #ffffff;
            padding: 40px 50px;
            border-radius: 20px;
            box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }

        .form-container h2 {
            font-size: 26px;
            margin-bottom: 20px;
            color: #333;
        }

        .form-container input {
            width: 100%;
            padding: 15px;
            margin: 15px 0;
            border: 1px solid #ccc;
            border-radius: 30px;
            font-size: 16px;
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-container input:focus {
            outline: none;
            border-color: #6a11cb;
            box-shadow: 0 0 6px rgba(106, 17, 203, 0.4);
        }

        .form-container input[type="submit"] {
            border: none;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: #fff;
            cursor: pointer;
            transition: background 0.3s, transform 0.2s;
        }

        .form-container input[type="submit"]:hover {
            background: linear-gradient(to right, #2575fc, #6a11cb);
            transform: translateY(-2px);
        }

        .form-container p {
            font-size: 14px;
            color: #777;
            margin-top: 20px;
        }

        .form-container p a {
            color: #2575fc;
            text-decoration: none;
            font-weight: bold;
        }

        .form-container p a:hover {
            text-decoration: underline;
        }

        .message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Create Your Account</h2>

    <form method="post" action="signup">
        <input type="text" name="firstName" placeholder="First Name" required>
        <input type="text" name="lastName" placeholder="Last Name" required>
        <input type="text" name="phoneNumber" placeholder="Phone Number" required>

        <input type="submit" value="SignUp" id="submit">
    </form>

    <p>Already have an account? <a href="login.jsp">Log in</a></p>
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
