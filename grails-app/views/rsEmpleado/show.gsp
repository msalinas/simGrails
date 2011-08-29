
<%@ page import="com.sim.empresa.RsEmpleado" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsEmpleado.show" default="Show RsEmpleado" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsEmpleado.list" default="RsEmpleado List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="rsEmpleado.new" default="New RsEmpleado" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsEmpleado.show" default="Show RsEmpleado" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${rsEmpleadoInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsEmpleadoInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.persona" default="Persona" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="rsPersona" action="show" id="${rsEmpleadoInstance?.persona?.id}">${rsEmpleadoInstance?.persona?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.puesto" default="Puesto" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="simCatPuesto" action="show" id="${rsEmpleadoInstance?.puesto?.id}">${rsEmpleadoInstance?.puesto?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.perfil" default="Perfil" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="simCatPerfil" action="show" id="${rsEmpleadoInstance?.perfil?.id}">${rsEmpleadoInstance?.perfil?.encodeAsHTML()}</g:link></td>
                                
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.sucursalPertenece" default="Sucursal Pertenece" />:</td>
                                
                                <td valign="top" class="value">${parametroSucursalPertenece}</td>
                                
                            </tr>


                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.fechaIngreso" default="Fecha Ingreso" />:</td>
                                
                                <td valign="top" class="value"><g:formatDate date="${rsEmpleadoInstance?.fechaIngreso}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.numeroNomina" default="Numero Nomina" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: rsEmpleadoInstance, field: "numeroNomina")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.esVigente" default="Es Vigente" />:</td>
                                
                                <td valign="top" class="value"><g:formatBoolean boolean="${rsEmpleadoInstance?.esVigente}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.asignarTodasSucursales" default="Asignar Todas Sucursales" />:</td>
                                
                                <td valign="top" class="value"><g:formatBoolean boolean="${rsEmpleadoInstance?.asignarTodasSucursales}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.sucursalesConAcceso" default="Sucursales Con Acceso" />:</td>
                                
                                <td  valign="top" style="text-align: left;" class="value">
                                    <ul>
                                    <g:each in="${rsEmpleadoInstance?.sucursalesConAcceso}" var="simSucursalInstance">
                                        <li><g:link controller="simSucursal" action="show" id="${simSucursalInstance.id}">${simSucursalInstance.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                    </ul>
                                </td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="rsEmpleado.regionalesConAcceso" default="Regionales Con Acceso" />:</td>
                                
                                <td  valign="top" style="text-align: left;" class="value">
                                    <ul>
                                    <g:each in="${rsEmpleadoInstance?.regionalesConAcceso}" var="simRegionalInstance">
                                        <li><g:link controller="simRegional" action="show" id="${simRegionalInstance.id}">${simRegionalInstance.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                    </ul>
                                </td>
                                
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
