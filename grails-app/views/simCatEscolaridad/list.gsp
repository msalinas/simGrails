
<%@ page import="com.sim.catalogo.SimCatEscolaridad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simCatEscolaridad.list" default="SimCatEscolaridad List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="simCatEscolaridad.new" default="New SimCatEscolaridad" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simCatEscolaridad.list" default="SimCatEscolaridad List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="simCatEscolaridad.id" />
                        
                   	    <g:sortableColumn property="claveEscolaridad" title="Clave Escolaridad" titleKey="simCatEscolaridad.claveEscolaridad" />
                        
                   	    <g:sortableColumn property="nombreEscolaridad" title="Nombre Escolaridad" titleKey="simCatEscolaridad.nombreEscolaridad" />
                        
                   	    <th><g:message code="simCatEscolaridad.rsConfEmpresa" default="Rs Conf Empresa" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${simCatEscolaridadInstanceList}" status="i" var="simCatEscolaridadInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${simCatEscolaridadInstance.id}">${fieldValue(bean: simCatEscolaridadInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: simCatEscolaridadInstance, field: "claveEscolaridad")}</td>
                        
                            <td>${fieldValue(bean: simCatEscolaridadInstance, field: "nombreEscolaridad")}</td>
                        
                            <td>${fieldValue(bean: simCatEscolaridadInstance, field: "rsConfEmpresa")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${simCatEscolaridadInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
