
<%@ page import="com.sim.catalogo.SimCatEscolaridad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="simCatEscolaridad.create" default="Create SimCatEscolaridad" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="simCatEscolaridad.list" default="SimCatEscolaridad List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="simCatEscolaridad.create" default="Create SimCatEscolaridad" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${simCatEscolaridadInstance}">
            <div class="errors">
                <g:renderErrors bean="${simCatEscolaridadInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="claveEscolaridad"><g:message code="simCatEscolaridad.claveEscolaridad" default="Clave Escolaridad" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simCatEscolaridadInstance, field: 'claveEscolaridad', 'errors')}">
                                    <g:textField name="claveEscolaridad" maxlength="15" value="${fieldValue(bean: simCatEscolaridadInstance, field: 'claveEscolaridad')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombreEscolaridad"><g:message code="simCatEscolaridad.nombreEscolaridad" default="Nombre Escolaridad" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simCatEscolaridadInstance, field: 'nombreEscolaridad', 'errors')}">
                                    <g:textField name="nombreEscolaridad" maxlength="50" value="${fieldValue(bean: simCatEscolaridadInstance, field: 'nombreEscolaridad')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rsConfEmpresa"><g:message code="simCatEscolaridad.rsConfEmpresa" default="Rs Conf Empresa" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: simCatEscolaridadInstance, field: 'rsConfEmpresa', 'errors')}">
                                    <g:select name="rsConfEmpresa.id" from="${com.sim.empresa.RsConfEmpresa.list()}" optionKey="id" value="${simCatEscolaridadInstance?.rsConfEmpresa?.id}"  />

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
