
<%@ page import="com.rs.gral.RsGralTelefono" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsGralTelefono.create" default="Create RsGralTelefono" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsGralTelefono.list" default="RsGralTelefono List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsGralTelefono.create" default="Create RsGralTelefono" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${rsGralTelefonoInstance}">
            <div class="errors">
                <g:renderErrors bean="${rsGralTelefonoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
							<g:if test="${rsGralTelefonoInstance?.regional?.id}">                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="regional"><g:message code="rsGralTelefono.regional" default="Regional" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'regional', 'errors')}">
	                                	<label>${rsGralTelefonoInstance?.regional?.nombreRegional}</label>
	                                	<g:hiddenField name='regional.id' value='${rsGralTelefonoInstance?.regional?.id}' />
	                                </td>
	                            </tr>
	                        </g:if>
           					<g:if test="${rsGralTelefonoInstance?.sucursal?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="sucursal"><g:message code="rsGralTelefono.sucursal" default="Sucursal" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'sucursal', 'errors')}">
	                                	<label>${rsGralTelefonoInstance?.sucursal?.nombreSucursal}</label>
	                                	<g:hiddenField name='sucursal.id' value='${rsGralTelefonoInstance?.sucursal?.id}' />
	                                </td>
	                            </tr>
                            </g:if>
                            <g:if test="${rsGralTelefonoInstance?.persona?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="persona"><g:message code="rsGralTelefono.persona" default="Persona" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'persona', 'errors')}">
	                                	<label>${rsGralTelefonoInstance?.persona}</label>
	                                	<g:hiddenField name='persona.id' value='${rsGralTelefonoInstance?.persona?.id}' />
	                                </td>
	                            </tr>
                            </g:if>                        
                            <g:if test="${rsGralTelefonoInstance?.negocio?.id}">
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="persona"><g:message code="rsGralTelefono.negocio" default="Negocio" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'negocio', 'errors')}">
	                                	<label>${rsGralTelefonoInstance?.negocio}</label>
	                                	<g:hiddenField name='negocio.id' value='${rsGralTelefonoInstance?.negocio?.id}' />
	                                </td>
	                            </tr>
                            </g:if>                        
                                                    
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefono"><g:message code="rsGralTelefono.telefono" default="Telefono" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'telefono', 'errors')}">
                                    <g:textField name="telefono" maxlength="15" value="${fieldValue(bean: rsGralTelefonoInstance, field: 'telefono')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcionTelefono"><g:message code="rsGralTelefono.descripcionTelefono" default="Descripcion Telefono" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsGralTelefonoInstance, field: 'descripcionTelefono', 'errors')}">
                                    <g:select name="descripcionTelefono.id" from="${com.sim.catalogo.SimCatDescTelefono.list()}" optionKey="id" value="${rsGralTelefonoInstance?.descripcionTelefono?.id}"  />

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
