
<%@ page import="com.sim.catalogo.SimCatTipoAsentamiento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simCatTipoAsentamiento.show" default="Show SimCatTipoAsentamiento" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="simCatTipoAsentamiento.list" default="SimCatTipoAsentamiento List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="simCatTipoAsentamiento.new" default="New SimCatTipoAsentamiento" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simCatTipoAsentamiento.show" default="Show SimCatTipoAsentamiento" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${simCatTipoAsentamientoInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="simCatTipoAsentamiento.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: simCatTipoAsentamientoInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="simCatTipoAsentamiento.claveTipoAsentamiento" default="Clave Tipo Asentamiento" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: simCatTipoAsentamientoInstance, field: "claveTipoAsentamiento")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="simCatTipoAsentamiento.nombreTipoAsentamiento" default="Nombre Tipo Asentamiento" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: simCatTipoAsentamientoInstance, field: "nombreTipoAsentamiento")}</td>
                                
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
