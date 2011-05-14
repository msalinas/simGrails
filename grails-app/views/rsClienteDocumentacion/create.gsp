
<%@ page import="com.sim.empresa.RsClienteDocumentacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsClienteDocumentacion.create" default="Create RsClienteDocumentacion" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsClienteDocumentacion.list" default="RsClienteDocumentacion List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsClienteDocumentacion.create" default="Create RsClienteDocumentacion" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${rsClienteDocumentacionInstance}">
            <div class="errors">
                <g:renderErrors bean="${rsClienteDocumentacionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cliente"><g:message code="rsClienteDocumentacion.cliente" default="Cliente" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsClienteDocumentacionInstance, field: 'cliente', 'errors')}">
                                    <g:select name="cliente.id" from="${com.sim.empresa.RsCliente.list()}" optionKey="id" value="${rsClienteDocumentacionInstance?.cliente?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="documento"><g:message code="rsClienteDocumentacion.documento" default="Documento" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsClienteDocumentacionInstance, field: 'documento', 'errors')}">
                                    <g:select name="documento.id" from="${com.sim.catalogo.SimCatDocumento.list()}" optionKey="id" value="${rsClienteDocumentacionInstance?.documento?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fechaRecibido"><g:message code="rsClienteDocumentacion.fechaRecibido" default="Fecha Recibido" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsClienteDocumentacionInstance, field: 'fechaRecibido', 'errors')}">
                                    <g:datePicker name="fechaRecibido" value="${rsClienteDocumentacionInstance?.fechaRecibido}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asesorVerifico"><g:message code="rsClienteDocumentacion.asesorVerifico" default="Asesor Verifico" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsClienteDocumentacionInstance, field: 'asesorVerifico', 'errors')}">
                                    <g:select name="asesorVerifico.id" from="${com.sim.empresa.RsEmpleado.
                                    	findAll('from RsEmpleado as e, SimCatPuesto as p where e.puesto = p and p.clavePuesto = ?', ['CooRie'])}" 
                                    	optionKey="id" value="${rsClienteDocumentacionInstance?.asesorVerifico?.id}" 
                                    	noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="documentacionCorrecta"><g:message code="rsClienteDocumentacion.documentacionCorrecta" default="Documentacion Correcta" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsClienteDocumentacionInstance, field: 'documentacionCorrecta', 'errors')}">
                                    <g:checkBox name="documentacionCorrecta" value="${rsClienteDocumentacionInstance?.documentacionCorrecta}" />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'create', 'default': 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
