<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        Notation
    </jsp:attribute>
    <jsp:body>
        <h2>Notation</h2>
        <p>
            Nom du critique: ${critique.prenom} ${critique.nom.toUpperCase()}<br/>
            Thème de prédilection: ${critique.theme.nom}
        </p>
        <form class="form-login" method="POST" action="${pageContext.request.contextPath}/notation">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Valider les changements</button>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Titre du livre</th>
                    <th scope="col">Note</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${livres}" var="livre" >
                    <tr>
                        <td>${livre.titre}</td>
                        <c:if test="${livre.note > 0}">
                        <td>${livre.note}</td>
                        </c:if>
                        <c:if test="${livre.note == 0}">
                            <td><input name="${livre.titre}" type="text" value="${notes[livre.titre]}"></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </jsp:body>
</t:template>