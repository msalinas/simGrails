
<%@ page import="com.sim.empresa.RsEmpleado" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsEmpleado.list" default="RsEmpleado List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsEmpleado.new" default="New RsEmpleado" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsEmpleado.list" default="RsEmpleado List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="rsEmpleado.id" />
                        
                   	    <th><g:message code="rsEmpleado.persona" default="Persona" /></th>
                   	    
                   	    <th><g:message code="rsEmpleado.puesto" default="Puesto" /></th>
                   	    
                   	    <th><g:message code="rsEmpleado.perfil" default="Perfil" /></th>
                   	    
                   	    <g:sortableColumn property="fechaIngreso" title="Fecha Ingreso" titleKey="rsEmpleado.fechaIngreso" />
                        
                   	    <g:sortableColumn property="numeroNomina" title="Numero Nomina" titleKey="rsEmpleado.numeroNomina" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rsEmpleadoInstanceList}" status="i" var="rsEmpleadoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rsEmpleadoInstance.id}">${fieldValue(bean: rsEmpleadoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: rsEmpleadoInstance, field: "persona")}</td>
                        
                            <td>${fieldValue(bean: rsEmpleadoInstance, field: "puesto")}</td>
                        
                            <td>${fieldValue(bean: rsEmpleadoInstance, field: "perfil")}</td>
                        
                            <td><g:formatDate date="${rsEmpleadoInstance.fechaIngreso}" /></td>
                        
                            <td>${fieldValue(bean: rsEmpleadoInstance, field: "numeroNomina")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rsEmpleadoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
