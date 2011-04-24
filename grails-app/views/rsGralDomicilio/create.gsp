
<%@ page import="com.rs.gral.RsGralDomicilio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralDomicilio.create" default="Create RsGralDomicilio" /></title>
		<g:javascript library="prototype" />
		<g:javascript src="domicilio.js" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsGralDomicilio.list" default="RsGralDomicilio List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralDomicilio.create" default="Create RsGralDomicilio" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${rsGralDomicilioInstance}">
            <div class="errors">
                <g:renderErrors bean="${rsGralDomicilioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="calle"><g:message code="rsGralDomicilio.calle" default="Calle" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'calle', 'errors')}">
                                    <g:textField name="calle" maxlength="100" value="${fieldValue(bean: rsGralDomicilioInstance, field: 'calle')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numeroInterior"><g:message code="rsGralDomicilio.numeroInterior" default="Numero Interior" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroInterior', 'errors')}">
                                    <g:textField name="numeroInterior" value="${fieldValue(bean: rsGralDomicilioInstance, field: 'numeroInterior')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numeroExterior"><g:message code="rsGralDomicilio.numeroExterior" default="Numero Exterior" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroExterior', 'errors')}">
                                    <g:textField name="numeroExterior" value="${fieldValue(bean: rsGralDomicilioInstance, field: 'numeroExterior')}" />

                                </td>
                            </tr>
 
							<g:domicilio estados="${com.rs.gral.RsGralEstado.list()}"/>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="esFiscal"><g:message code="rsGralDomicilio.esFiscal" default="Es Fiscal" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'esFiscal', 'errors')}">
                                    <g:checkBox name="esFiscal" value="${rsGralDomicilioInstance?.esFiscal}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comentarios"><g:message code="rsGralDomicilio.comentarios" default="Comentarios" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'comentarios', 'errors')}">
                                    <g:textArea name="comentarios" rows="5" cols="40" value="${fieldValue(bean: rsGralDomicilioInstance, field: 'comentarios')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rsConfEmpresa"><g:message code="rsGralDomicilio.rsConfEmpresa" default="Rs Conf Empresa" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsConfEmpresa', 'errors')}">
                                    <g:select name="rsConfEmpresa.id" from="${com.sim.empresa.RsConfEmpresa.list()}" optionKey="id" value="${rsGralDomicilioInstance?.rsConfEmpresa?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="regional"><g:message code="rsGralDomicilio.regional" default="Regional" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'regional', 'errors')}">
                                    <g:select name="regional.id" from="${com.sim.regional.SimRegional.list()}" optionKey="id" value="${rsGralDomicilioInstance?.regional?.id}" noSelection="['null': '']" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sucursal"><g:message code="rsGralDomicilio.sucursal" default="Sucursal" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralDomicilioInstance, field: 'sucursal', 'errors')}">
                                    <g:select name="sucursal.id" from="${com.sim.regional.SimSucursal.list()}" optionKey="id" value="${rsGralDomicilioInstance?.sucursal?.id}" noSelection="['null': '']" />

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
