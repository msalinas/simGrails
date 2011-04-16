
<%@ page import="com.sim.prueba.Country" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="country.show" default="Show Country" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="country.list" default="Country List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="country.new" default="New Country" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="country.show" default="Show Country" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${countryInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="country.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: countryInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="country.abbr" default="Abbr" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: countryInstance, field: "abbr")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="country.cities" default="Cities" />:</td>
                                
                                <td  valign="top" style="text-align: left;" class="value">
                                    <ul>
                                    <g:each in="${countryInstance?.cities}" var="cityInstance">
                                        <li><g:link controller="city" action="show" id="${cityInstance.id}">${cityInstance.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                    </ul>
                                </td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="country.language" default="Language" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: countryInstance, field: "language")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="country.name" default="Name" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: countryInstance, field: "name")}</td>
                                
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
