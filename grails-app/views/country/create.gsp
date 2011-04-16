
<%@ page import="com.sim.prueba.Country" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="country.create" default="Create Country" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="country.list" default="Country List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="country.create" default="Create Country" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${countryInstance}">
            <div class="errors">
                <g:renderErrors bean="${countryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                                    <label for="language"><g:message code="country.language" default="Language" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'language', 'errors')}">
                                    <g:textField name="language" value="${fieldValue(bean: countryInstance, field: 'language')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="country.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: countryInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${fieldValue(bean: countryInstance, field: 'name')}" />

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
