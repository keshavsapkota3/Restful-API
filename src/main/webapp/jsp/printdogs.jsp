<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <title>Insert title here</title>
    </head>

    <body>
      <h1>All the dogs</h1>
      <ul>
        <c:forEach var="dog" items="${requestScope.dogs }">
          <li>${dog.id}: ${dog.breed} / ${dog.weight}</li>
        </c:forEach>
      </ul>
    </body>

    </html>