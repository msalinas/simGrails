
<%@ page import="com.sim.prueba.Country" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="country.list" default="Country List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="country.new" default="New Country" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="country.list" default="Country List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="country.id" />
                        
                   	    <g:sortableColumn property="abbr" title="Abbr" titleKey="country.abbr" />
                        
                   	    <g:sortableColumn property="language" title="Language" titleKey="country.language" />
                        
                   	    <g:sortableColumn property="nameCountry" title="Name Country" titleKey="country.nameCountry" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${countryInstanceList}" status="i" var="countryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${countryInstance.id}">${fieldValue(bean: countryInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: countryInstance, field: "abbr")}</td>
                        
                            <td>${fieldValue(bean: countryInstance, field: "language")}</td>
                        
                            <td>${fieldValue(bean: countryInstance, field: "nameCountry")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${countryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
