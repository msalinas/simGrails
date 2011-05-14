
<%@ page import="com.sim.empresa.RsClienteDocumentacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsClienteDocumentacion.list" default="RsClienteDocumentacion List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsClienteDocumentacion.new" default="New RsClienteDocumentacion" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsClienteDocumentacion.list" default="RsClienteDocumentacion List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="rsClienteDocumentacion.id" />
                        
                   	    <th><g:message code="rsClienteDocumentacion.cliente" default="Cliente" /></th>
                   	    
                   	    <th><g:message code="rsClienteDocumentacion.documento" default="Documento" /></th>
                   	    
                   	    <g:sortableColumn property="fechaRecibido" title="Fecha Recibido" titleKey="rsClienteDocumentacion.fechaRecibido" />
                        
                   	    <th><g:message code="rsClienteDocumentacion.asesorVerifico" default="Asesor Verifico" /></th>
                   	    
                   	    <g:sortableColumn property="documentacionCorrecta" title="Documentacion Correcta" titleKey="rsClienteDocumentacion.documentacionCorrecta" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rsClienteDocumentacionInstanceList}" status="i" var="rsClienteDocumentacionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rsClienteDocumentacionInstance.id}">${fieldValue(bean: rsClienteDocumentacionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: rsClienteDocumentacionInstance, field: "cliente")}</td>
                        
                            <td>${fieldValue(bean: rsClienteDocumentacionInstance, field: "documento")}</td>
                        
                            <td><g:formatDate date="${rsClienteDocumentacionInstance.fechaRecibido}" /></td>
                        
                            <td>${fieldValue(bean: rsClienteDocumentacionInstance, field: "asesorVerifico")}</td>
                        
                            <td><g:formatBoolean boolean="${rsClienteDocumentacionInstance.documentacionCorrecta}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rsClienteDocumentacionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
