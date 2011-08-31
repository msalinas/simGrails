
<%@ page import="com.rs.gral.RsGralDomicilio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralDomicilio.list" default="RsGralDomicilio List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsGralDomicilio.new" default="New RsGralDomicilio" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralDomicilio.list" default="RsGralDomicilio List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="rsGralDomicilio.id" />
                        
                   	    <g:sortableColumn property="calle" title="Calle" titleKey="rsGralDomicilio.calle" />
                        
                   	    <g:sortableColumn property="numeroInterior" title="Numero Interior" titleKey="rsGralDomicilio.numeroInterior" />
                        
                   	    <th><g:message code="rsGralDomicilio.rsGralAsentamiento" default="Rs Gral Asentamiento" /></th>
                   	    
                   	    <g:sortableColumn property="esFiscal" title="Es Fiscal" titleKey="rsGralDomicilio.esFiscal" />
                   	    
                   	    <th><g:message code="rsGralDomicilio.regional" default="Regional" /></th>
                   	    
                   	    <th><g:message code="rsGralDomicilio.sucursal" default="Sucursal" /></th>
                   	    
                   	    <th><g:message code="rsGralDomicilio.persona" default="Persona" /></th>
                   	    
                   	    <th><g:message code="rsGralDomicilio.negocio" default="Negocio" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rsGralDomicilioInstanceList}" status="i" var="rsGralDomicilioInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rsGralDomicilioInstance.id}">${fieldValue(bean: rsGralDomicilioInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "calle")}</td>
                        
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "numeroInterior")}</td>
                        
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "rsGralAsentamiento")}</td>
                        
                            <td><g:formatBoolean boolean="${rsGralDomicilioInstance.esFiscal}" /></td>
                            
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "regional")}</td>
                            
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "sucursal")}</td>
                        
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "persona")}</td>
                            
                            <td>${fieldValue(bean: rsGralDomicilioInstance, field: "negocio")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rsGralDomicilioInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
