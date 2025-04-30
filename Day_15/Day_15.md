# Spring Security Basic Authentication implement
# Introduction to Using Thymeleaf in Spring
## create Thymeleaf and some operation
# All CRUD Operation
### Set User Role
### See User present User Role
# code for Role 
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>User Role Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f0f5;
            padding: 40px;
        }

        .container {
            background: white;
            max-width: 500px;
            margin: auto;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn {
            width: 48%;
            padding: 10px;
            margin-top: 20px;
            font-weight: bold;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }

        .btn-see {
            background-color: #007bff;
            color: white;
        }

        .btn-set {
            background-color: #28a745;
            color: white;
            float: right;
        }

        .role-box {
            margin-top: 20px;
            background: #eef;
            padding: 10px;
            border-radius: 8px;
        }

        .role-box span {
            display: inline-block;
            background: #6c757d;
            color: white;
            padding: 5px 12px;
            margin: 5px;
            border-radius: 20px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>User Role Management</h2>

    <form method="post" th:action="@{/seeRoles}" th:object="${userForm}">
        <label for="userId">User ID:</label>
        <input type="text" th:field="*{userId}" placeholder="Enter user ID"/>

        <button type="submit" class="btn btn-see">See Present Roles</button>
    </form>

    <form method="post" th:action="@{/setRole}" th:object="${userForm}">
        <label for="role">Set New Role:</label>
        <select th:field="*{roleName}">
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
            <option value="MODERATOR">MODERATOR</option>
            <option value="GUEST">GUEST</option>
        </select>

        <input type="hidden" th:field="*{userId}" />

        <button type="submit" class="btn btn-set">Set New Role</button>
    </form>

    <div class="role-box" th:if="${presentRoles}">
        <label>Present Roles:</label>
        <div>
            <span th:each="role : ${presentRoles}" th:text="${role}">ROLE</span>
        </div>
    </div>
</div>
</body>
</html>

# Code for User CRUD
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 30px;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .button-group {
            margin-top: 25px;
            display: flex;
            justify-content: space-between;
        }

        button {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            color: white;
        }

        .upload-btn {
            background-color: #28a745;
        }

        .find-btn {
            background-color: #007bff;
        }

        .delete-btn {
            background-color: #dc3545;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>User Details</h2>

    <form th:action="@{/user/save}" method="post">
        <label for="id">ID</label>
        <input type="text" id="id" name="id" th:value="${user.id}"/>

        <label for="username">User Name</label>
        <input type="text" id="username" name="username" th:value="${user.username}"/>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" th:value="${user.password}"/>

        <div class="button-group">
            <button type="submit" class="upload-btn">Upload</button>
            <button type="submit" formaction="/user/find" class="find-btn">Find</button>
            <button type="submit" formaction="/user/delete" class="delete-btn">Delete</button>
        </div>
    </form>
</div>

</body>
</html>
