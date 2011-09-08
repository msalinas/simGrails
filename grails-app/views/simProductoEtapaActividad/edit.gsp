
<%@ page import="com.sim.producto.SimProductoEtapaActividad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simProductoEtapaActividad.edit" default="Edit SimProductoEtapaActividad" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="simProductoEtapaActividad.list" default="SimProductoEtapaActividad List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="simProductoEtapaActividad.new" default="New SimProductoEtapaActividad" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simProductoEtapaActividad.edit" default="Edit SimProductoEtapaActividad" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${simProductoEtapaActividadInstance}">
            <div class="errors">
                <g:renderErrors bean="${simProductoEtapaActividadInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${simProductoEtapaActividadInstance?.id}" />
                <g:hiddenField name="version" value="${simProductoEtapaActividadInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="producto"><g:message code="simProductoEtapaActividad.producto" default="Producto" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simProductoEtapaActividadInstance, field: 'producto', 'errors')}">
                                    <g:select name="producto.id" from="${com.sim.producto.SimProducto.list()}" optionKey="id" value="${simProductoEtapaActividadInstance?.producto?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="etapaActividad"><g:message code="simProductoEtapaActividad.etapaActividad" default="Etapa Actividad" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simProductoEtapaActividadInstance, field: 'etapaActividad', 'errors')}">
                                    <g:select name="etapaActividad.id" from="${com.sim.catalogo.SimCatEtapaActividad.list()}" optionKey="id" value="${simProductoEtapaActividadInstance?.etapaActividad?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orden"><g:message code="simProductoEtapaActividad.orden" default="Orden" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simProductoEtapaActividadInstance, field: 'orden', 'errors')}">
                                    <g:select name="orden" from="${1..35}" value="${simProductoEtapaActividadInstance?.orden}"  />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
