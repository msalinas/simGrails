
<%@ page import="com.rs.gral.RsGralDomicilio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralDomicilio.show" default="Show RsGralDomicilio" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsGralDomicilio.list" default="RsGralDomicilio List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsGralDomicilio.new" default="New RsGralDomicilio" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralDomicilio.show" default="Show RsGralDomicilio" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${rsGralDomicilioInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                      		<g:if test="${rsGralDomicilioInstance?.regional?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name"><g:message code="rsGralDomicilio.regional" default="Regional" />:</td>
	                                <td valign="top" class="value"><g:link controller="simRegional" action="show" id="${rsGralDomicilioInstance?.regional?.id}">${rsGralDomicilioInstance?.regional?.encodeAsHTML()}</g:link></td>
	                            </tr>
                        	</g:if>
           					<g:if test="${rsGralDomicilioInstance?.sucursal?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name"><g:message code="rsGralDomicilio.sucursal" default="Sucursal" />:</td>
	                                <td valign="top" class="value"><g:link controller="simSucursal" action="show" id="${rsGralDomicilioInstance?.sucursal?.id}">${rsGralDomicilioInstance?.sucursal?.encodeAsHTML()}</g:link></td>
	                            </tr>
                        	</g:if>                          
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralDomicilioInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.calle" default="Calle" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralDomicilioInstance, field: "calle")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.numeroInterior" default="Numero Interior" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralDomicilioInstance, field: "numeroInterior")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.numeroExterior" default="Numero Exterior" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralDomicilioInstance, field: "numeroExterior")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.rsGralAsentamiento" default="Rs Gral Asentamiento" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsGralAsentamiento" action="show" id="${rsGralDomicilioInstance?.rsGralAsentamiento?.id}">${rsGralDomicilioInstance?.rsGralAsentamiento?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.esFiscal" default="Es Fiscal" />:</td>
                                
                                <td valign="top" class="value"><g:formatBoolean boolean="${rsGralDomicilioInstance?.esFiscal}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.comentarios" default="Comentarios" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralDomicilioInstance, field: "comentarios")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralDomicilio.rsConfEmpresa" default="Rs Conf Empresa" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsConfEmpresa" action="show" id="${rsGralDomicilioInstance?.rsConfEmpresa?.id}">${rsGralDomicilioInstance?.rsConfEmpresa?.encodeAsHTML()}</g:link></td>
                                
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
