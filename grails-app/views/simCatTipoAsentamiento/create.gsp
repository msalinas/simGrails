
<%@ page import="com.sim.catalogo.SimCatTipoAsentamiento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simCatTipoAsentamiento.create" default="Create SimCatTipoAsentamiento" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="simCatTipoAsentamiento.list" default="SimCatTipoAsentamiento List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simCatTipoAsentamiento.create" default="Create SimCatTipoAsentamiento" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${simCatTipoAsentamientoInstance}">
            <div class="errors">
                <g:renderErrors bean="${simCatTipoAsentamientoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="claveTipoAsentamiento"><g:message code="simCatTipoAsentamiento.claveTipoAsentamiento" default="Clave Tipo Asentamiento" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simCatTipoAsentamientoInstance, field: 'claveTipoAsentamiento', 'errors')}">
                                    <g:textField name="claveTipoAsentamiento" maxlength="15" value="${fieldValue(bean: simCatTipoAsentamientoInstance, field: 'claveTipoAsentamiento')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombreTipoAsentamiento"><g:message code="simCatTipoAsentamiento.nombreTipoAsentamiento" default="Nombre Tipo Asentamiento" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simCatTipoAsentamientoInstance, field: 'nombreTipoAsentamiento', 'errors')}">
                                    <g:textField name="nombreTipoAsentamiento" maxlength="50" value="${fieldValue(bean: simCatTipoAsentamientoInstance, field: 'nombreTipoAsentamiento')}" />

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
