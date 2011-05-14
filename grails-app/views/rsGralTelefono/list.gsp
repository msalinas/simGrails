
<%@ page import="com.rs.gral.RsGralTelefono" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralTelefono.list" default="RsGralTelefono List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsGralTelefono.new" default="New RsGralTelefono" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralTelefono.list" default="RsGralTelefono List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="rsGralTelefono.id" />
                        
                   	    <g:sortableColumn property="telefono" title="Telefono" titleKey="rsGralTelefono.telefono" />
                        
                   	    <th><g:message code="rsGralTelefono.descripcionTelefono" default="Descripcion Telefono" /></th>
                   	    
                   	    <th><g:message code="rsGralTelefono.regional" default="Regional" /></th>
                   	    
                   	    <th><g:message code="rsGralTelefono.sucursal" default="Sucursal" /></th>
                   	    
                   	    <th><g:message code="rsGralTelefono.persona" default="Persona" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rsGralTelefonoInstanceList}" status="i" var="rsGralTelefonoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rsGralTelefonoInstance.id}">${fieldValue(bean: rsGralTelefonoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: rsGralTelefonoInstance, field: "telefono")}</td>
                        
                            <td>${fieldValue(bean: rsGralTelefonoInstance, field: "descripcionTelefono")}</td>
                        
                            <td>${fieldValue(bean: rsGralTelefonoInstance, field: "regional")}</td>
                            
                            <td>${fieldValue(bean: rsGralTelefonoInstance, field: "sucursal")}</td>
                        
                            <td>${fieldValue(bean: rsGralTelefonoInstance, field: "persona")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rsGralTelefonoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
