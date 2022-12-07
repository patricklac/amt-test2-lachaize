<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        Liste des titres
    </jsp:attribute>
    <jsp:body>
        <h2>Liste des titres</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Titre du livre</th>
                <th scope="col">Nom du thème</th>
                <th scope="col">Sélection</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${livres}" var="livre" >
                <tr>
                    <td>${livre.titre}</td>
                    <td>${livre.theme.nom}</td>
                    <td>${livre.selection == true ? "OK" : ""}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:template>