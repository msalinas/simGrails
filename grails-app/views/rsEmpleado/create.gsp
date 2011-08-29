
<%@ page import="com.sim.empresa.RsEmpleado" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="rsEmpleado.create" default="Create RsEmpleado" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="rsEmpleado.list" default="RsEmpleado List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="rsEmpleado.create" default="Create RsEmpleado" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${rsEmpleadoInstance}">
            <div class="errors">
                <g:renderErrors bean="${rsEmpleadoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="persona"><g:message code="rsEmpleado.persona" default="Persona" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'persona', 'errors')}">
                                    <g:select name="persona.id" from="${com.rs.gral.RsPersona.list()}" optionKey="id" value="${rsEmpleadoInstance?.persona?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="puesto"><g:message code="rsEmpleado.puesto" default="Puesto" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'puesto', 'errors')}">
                                    <g:select name="puesto.id" from="${com.sim.catalogo.SimCatPuesto.list()}" optionKey="id" value="${rsEmpleadoInstance?.puesto?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="perfil"><g:message code="rsEmpleado.perfil" default="Perfil" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'perfil', 'errors')}">
                                    <g:select name="perfil.id" from="${com.sim.catalogo.SimCatPerfil.list()}" optionKey="id" value="${rsEmpleadoInstance?.perfil?.id}"  />

                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sucursalPertenece"><g:message code="rsEmpleado.sucursalPertenece" default="Sucursal Pertenece" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'sucursalPertenece', 'errors')}">
                                    <g:select name="sucursalPertenece" from="${com.sim.regional.SimSucursal.list()}" optionKey="id" value="${rsEmpleadoInstance?.sucursalPertenece}"  />

                                </td>
                            </tr>

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fechaIngreso"><g:message code="rsEmpleado.fechaIngreso" default="Fecha Ingreso" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'fechaIngreso', 'errors')}">
                                    <g:datePicker name="fechaIngreso" value="${rsEmpleadoInstance?.fechaIngreso}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numeroNomina"><g:message code="rsEmpleado.numeroNomina" default="Numero Nomina" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'numeroNomina', 'errors')}">
                                    <g:textField name="numeroNomina" value="${fieldValue(bean: rsEmpleadoInstance, field: 'numeroNomina')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="esVigente"><g:message code="rsEmpleado.esVigente" default="Es Vigente" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'esVigente', 'errors')}">
                                    <g:checkBox name="esVigente" value="${rsEmpleadoInstance?.esVigente}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asignarTodasSucursales"><g:message code="rsEmpleado.asignarTodasSucursales" default="Asignar Todas Sucursales" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rsEmpleadoInstance, field: 'asignarTodasSucursales', 'errors')}">
                                    <g:checkBox name="asignarTodasSucursales" value="${rsEmpleadoInstance?.asignarTodasSucursales}" />

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
