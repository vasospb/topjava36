<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список еды <%= request.getParameter("name") %></title>
    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .excess {
            background-color: #ffb3b3; /* светло-красный фон */
        }
    </style>
</head>
<body>
<h2 style="text-align:center;">Список приёмов пищи ${param.name} </h2>

<table>
    <tr>
        <th>Дата и время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Избыток</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.excess ? 'excess' : ''}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><c:out value="${meal.excess ? 'Да' : 'Нет'}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
