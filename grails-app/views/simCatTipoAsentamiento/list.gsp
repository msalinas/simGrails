
<%@ page import="com.sim.catalogo.SimCatTipoAsentamiento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simCatTipoAsentamiento.list" default="SimCatTipoAsentamiento List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="simCatTipoAsentamiento.new" default="New SimCatTipoAsentamiento" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simCatTipoAsentamiento.list" default="SimCatTipoAsentamiento List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="simCatTipoAsentamiento.id" />
                        
                   	    <g:sortableColumn property="claveTipoAsentamiento" title="Clave Tipo Asentamiento" titleKey="simCatTipoAsentamiento.claveTipoAsentamiento" />
                        
                   	    <g:sortableColumn property="nombreTipoAsentamiento" title="Nombre Tipo Asentamiento" titleKey="simCatTipoAsentamiento.nombreTipoAsentamiento" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${simCatTipoAsentamientoInstanceList}" status="i" var="simCatTipoAsentamientoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${simCatTipoAsentamientoInstance.id}">${fieldValue(bean: simCatTipoAsentamientoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: simCatTipoAsentamientoInstance, field: "claveTipoAsentamiento")}</td>
                        
                            <td>${fieldValue(bean: simCatTipoAsentamientoInstance, field: "nombreTipoAsentamiento")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${simCatTipoAsentamientoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
