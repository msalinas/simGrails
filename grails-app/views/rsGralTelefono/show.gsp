
<%@ page import="com.rs.gral.RsGralTelefono" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralTelefono.show" default="Show RsGralTelefono" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsGralTelefono.list" default="RsGralTelefono List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsGralTelefono.new" default="New RsGralTelefono" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralTelefono.show" default="Show RsGralTelefono" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${rsGralTelefonoInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>

							<g:if test="${rsGralTelefonoInstance?.regional?.id}">                            
	                            <tr class="prop">
	                                <td valign="top" class="name"><g:message code="rsGralTelefono.regional" default="Regional" />:</td>
	                                
	                                <td valign="top" class="value"><g:link controller="simRegional" action="show" id="${rsGralTelefonoInstance?.regional?.id}">${rsGralTelefonoInstance?.regional?.encodeAsHTML()}</g:link></td>
	                                
	                            </tr>
	                        </g:if>
           					<g:if test="${rsGralTelefonoInstance?.sucursal?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name"><g:message code="rsGralTelefono.sucursal" default="Sucursal" />:</td>
	                                
	                                <td valign="top" class="value"><g:link controller="simSucursal" action="show" id="${rsGralTelefonoInstance?.sucursal?.id}">${rsGralTelefonoInstance?.sucursal?.encodeAsHTML()}</g:link></td>
	                                
	                            </tr>
                            </g:if>
          					<g:if test="${rsGralTelefonoInstance?.persona?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name"><g:message code="rsGralTelefono.persona" default="Persona" />:</td>
	                                
	                                <td valign="top" class="value"><g:link controller="rsPersona" action="show" id="${rsGralTelefonoInstance?.persona?.id}">${rsGralTelefonoInstance?.persona?.encodeAsHTML()}</g:link></td>
	                                
	                            </tr>
                            </g:if>                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralTelefono.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralTelefonoInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralTelefono.telefono" default="Telefono" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsGralTelefonoInstance, field: "telefono")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralTelefono.descripcionTelefono" default="Descripcion Telefono" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="simCatDescTelefono" action="show" id="${rsGralTelefonoInstance?.descripcionTelefono?.id}">${rsGralTelefonoInstance?.descripcionTelefono?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsGralTelefono.rsConfEmpresa" default="Rs Conf Empresa" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsConfEmpresa" action="show" id="${rsGralTelefonoInstance?.rsConfEmpresa?.id}">${rsGralTelefonoInstance?.rsConfEmpresa?.encodeAsHTML()}</g:link></td>
                                
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
