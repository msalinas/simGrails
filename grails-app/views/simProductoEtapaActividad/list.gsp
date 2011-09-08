
<%@ page import="com.sim.producto.SimProductoEtapaActividad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simProductoEtapaActividad.list" default="SimProductoEtapaActividad List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="simProductoEtapaActividad.new" default="New SimProductoEtapaActividad" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simProductoEtapaActividad.list" default="SimProductoEtapaActividad List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="simProductoEtapaActividad.id" />
                        
                   	    <th><g:message code="simProductoEtapaActividad.producto" default="Producto" /></th>
                   	    
                   	    <th><g:message code="simProductoEtapaActividad.etapaActividad" default="Etapa Actividad" /></th>
                   	    
                   	    <g:sortableColumn property="orden" title="Orden" titleKey="simProductoEtapaActividad.orden" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${simProductoEtapaActividadInstanceList}" status="i" var="simProductoEtapaActividadInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${simProductoEtapaActividadInstance.id}">${fieldValue(bean: simProductoEtapaActividadInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: simProductoEtapaActividadInstance, field: "producto")}</td>
                        
                            <td>${fieldValue(bean: simProductoEtapaActividadInstance, field: "etapaActividad")}</td>
                        
                            <td>${fieldValue(bean: simProductoEtapaActividadInstance, field: "orden")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${simProductoEtapaActividadInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
