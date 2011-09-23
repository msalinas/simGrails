<!DOCTYPE html>
<html>
<head>
<title>SIM &raquo; <g:layoutTitle default="Bienvenido" /></title>
<nav:resources />
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'sim.jpg')}" type="image/x-icon" />
<g:layoutHead />
<g:javascript library="application" />
</head>
<body>

	<div id="spinner" class="spinner" style="display: none;">
		<img src="${resource(dir:'images',file:'spinner.gif')}"
			alt="${message(code:'spinner.alt',default:'Loading...')}" />
	</div>

	<table>
		<tr>
			<td>
				<div id="simLogo">
					<a href="http://www.google.com"><img
						src="${resource(dir:'images',file:'sim.jpg')}" alt="Sim"
						border="0" /> </a>
				</div>
			</td>
			<td>
				<div id="login" align="right">
					<sec:ifNotLoggedIn>
						<g:link controller="login" action="auth">
							<h2>Entrar</h2>
						</g:link>
						
					</sec:ifNotLoggedIn>
					<sec:ifLoggedIn>
						<h2>Bienvenido: <sec:username /></h2>
						<br>
						<h3><g:link controller="logout">Salir</g:link></h3>
					</sec:ifLoggedIn>
				</div>
			</td>
		</tr>
	</table>

	<nav:render group="tabs" />
	
	<nav:renderSubItems group="tabs" />
	
	<g:layoutBody />

</body>
</html>
