
<%@ page import="com.sim.prueba.Country" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="country.edit" default="Edit Country" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="country.list" default="Country List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="country.new" default="New Country" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="country.edit" default="Edit Country" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${countryInstance}">
            <div class="errors">
                <g:renderErrors bean="${countryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${countryInstance?.id}" />
                <g:hiddenField name="version" value="${countryInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="abbr"><g:message code="country.abbr" default="Abbr" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'abbr', 'errors')}">
                                    <g:textField name="abbr" value="${fieldValue(bean: countryInstance, field: 'abbr')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cities"><g:message code="country.cities" default="Cities" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'cities', 'errors')}">
                                    
<ul>
<g:each in="${countryInstance?.cities}" var="cityInstance">
    <li><g:link controller="city" action="show" id="${cityInstance.id}">${cityInstance?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="city" params="['country.id': countryInstance?.id]" action="create"><g:message code="city.new" default="New City" /></g:link>


                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="language"><g:message code="country.language" default="Language" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'language', 'errors')}">
                                    <g:textField name="language" value="${fieldValue(bean: countryInstance, field: 'language')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameCountry"><g:message code="country.nameCountry" default="Name Country" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'nameCountry', 'errors')}">
                                    <g:textField name="nameCountry" value="${fieldValue(bean: countryInstance, field: 'nameCountry')}" />

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
