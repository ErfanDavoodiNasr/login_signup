<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
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
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 350px;
        }

        .form-container h2 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        .form-container input {
            width: 100%;
            padding: 12px;
            margin: 15px 0;
            border: 1px solid #ccc;
            border-radius: 25px;
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
            margin-top: 10px;
        }

        .form-container p a {
            color: #2575fc;
            text-decoration: none;
            font-weight: bold;
        }

        .form-container p a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Verification code</h2>
    <form method="post" action="login">
        <input type="text" name="authCode" id="authCode" placeholder="Enter your verification code" required>
        <input type="submit" name="action" value="verifyCode">
    </form>

</div>

<script>
    <%
        if (request.getAttribute("message") != null) {
    %>
    alert('<%= request.getAttribute("message") %>');
    <% } %>
</script>

</body>
</html>