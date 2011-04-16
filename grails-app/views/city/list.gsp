
<%@ page import="com.sim.prueba.City" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="city.list" default="City List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="city.new" default="New City" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="city.list" default="City List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="city.id" />
                        
                   	    <th><g:message code="city.country" default="Country" /></th>
                   	    
                   	    <g:sortableColumn property="nameCity" title="Name City" titleKey="city.nameCity" />
                        
                   	    <g:sortableColumn property="timezone" title="Timezone" titleKey="city.timezone" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cityInstanceList}" status="i" var="cityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cityInstance.id}">${fieldValue(bean: cityInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: cityInstance, field: "country")}</td>
                        
                            <td>${fieldValue(bean: cityInstance, field: "nameCity")}</td>
                        
                            <td>${fieldValue(bean: cityInstance, field: "timezone")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
