
<%@ page import="com.sim.empresa.RsClienteDocumentacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsClienteDocumentacion.show" default="Show RsClienteDocumentacion" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsClienteDocumentacion.list" default="RsClienteDocumentacion List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsClienteDocumentacion.new" default="New RsClienteDocumentacion" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsClienteDocumentacion.show" default="Show RsClienteDocumentacion" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${rsClienteDocumentacionInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsClienteDocumentacionInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.cliente" default="Cliente" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsCliente" action="show" id="${rsClienteDocumentacionInstance?.cliente?.id}">${rsClienteDocumentacionInstance?.cliente?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.documento" default="Documento" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="simCatDocumento" action="show" id="${rsClienteDocumentacionInstance?.documento?.id}">${rsClienteDocumentacionInstance?.documento?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.fechaRecibido" default="Fecha Recibido" />:</td>
                                
                                <td valign="top" class="value"><g:formatDate date="${rsClienteDocumentacionInstance?.fechaRecibido}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.asesorVerifico" default="Asesor Verifico" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsEmpleado" action="show" id="${rsClienteDocumentacionInstance?.asesorVerifico?.id}">${rsClienteDocumentacionInstance?.asesorVerifico?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsClienteDocumentacion.documentacionCorrecta" default="Documentacion Correcta" />:</td>
                                
                                <td valign="top" class="value"><g:formatBoolean boolean="${rsClienteDocumentacionInstance?.documentacionCorrecta}" /></td>
                                
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'edit', 'default': 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
