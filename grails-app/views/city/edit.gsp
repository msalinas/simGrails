
<%@ page import="com.sim.prueba.City" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="city.edit" default="Edit City" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="city.list" default="City List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="city.new" default="New City" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="city.edit" default="Edit City" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${cityInstance}">
            <div class="errors">
                <g:renderErrors bean="${cityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${cityInstance?.id}" />
                <g:hiddenField name="version" value="${cityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="country"><g:message code="city.country" default="Country" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'country', 'errors')}">
                                    <g:select name="country.id" from="${com.sim.prueba.Country.list()}" optionKey="id" value="${cityInstance?.country?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameCity"><g:message code="city.nameCity" default="Name City" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'nameCity', 'errors')}">
                                    <g:textField name="nameCity" value="${fieldValue(bean: cityInstance, field: 'nameCity')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timezone"><g:message code="city.timezone" default="Timezone" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'timezone', 'errors')}">
                                    <g:textField name="timezone" value="${fieldValue(bean: cityInstance, field: 'timezone')}" />

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
