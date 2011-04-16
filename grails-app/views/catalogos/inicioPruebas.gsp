<html>
	<head>
		<title>Pruebas</title>
		<meta name="layout" content="main" />
		<g:javascript library="prototype" />
	</head>
	<body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" controller="country" action="list"><g:message code="country.list" default="Country List" /></g:link></span>
            <span class="menuButton"><g:link class="list" controller="city" action="list"><g:message code="city.list" default="City List" /></g:link></span>
        </div>			
	
		<form>
			<br />
			<g:link controller="pruebas" action="cambiarSelect">Cambiar Select</g:link>
		</form>
	</body>
</html>