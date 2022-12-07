<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        Identification
    </jsp:attribute>
    <jsp:body>
        <h2>Identification</h2>
        <form class="form-login" method="POST" action="${pageContext.request.contextPath}/login">
            <h1 class="h3 mb-3 font-weight-normal">Connectez-vous</h1>
            <label for="prenom" class="sr-only">Prenom</label>
            <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Prenom" required="" autofocus="">
            <label for="nom" class="sr-only">Nom</label>
            <input type="text" name="nom" id="nom" class="form-control" placeholder="Nom" required="">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>

        </form>
    </jsp:body>
</t:template>