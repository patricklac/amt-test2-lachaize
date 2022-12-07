<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="template amt-test2" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>

<!DOCTYPE html >
<html xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html">
<h:head>
    <meta charset="UTF-8" />
    <title><jsp:invoke fragment="title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</h:head>
<h:body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
           <div class="navbar-nav navigation-links">
                <a href="${pageContext.request.contextPath}/">Titres</a> &NonBreakingSpace;
                <a href="${pageContext.request.contextPath}/notation">Notation</a> &NonBreakingSpace;
                <a href="${pageContext.request.contextPath}/logout">Deconnexion</a>
            </div>
    </nav>
    <main role="main" class="main-container">
        <div class="container">
            <c:if test="${not empty alertmessage}">
                <div class="alert ${alerttype}" role="alert">
                        ${alertmessage}
                </div>
            </c:if>
            <jsp:doBody/>
        </div>
    </main>
    <br/>
</h:body>
</html>