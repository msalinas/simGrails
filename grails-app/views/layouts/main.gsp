<!DOCTYPE html>
<html>
    <head>
        <title>SIM &raquo; <g:layoutTitle default="Bienvenido" /></title>
		<nav:resources/>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>

        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="simLogo">
			<a href="http://www.rapidsist.com"><img src="${resource(dir:'images',file:'sim.jpg')}" alt="Sim" border="0" /></a>
			<sec:ifNotLoggedIn>
				<g:link controller="login" action="auth">Entrar</g:link>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<sec:username /> (<g:link controller="logout">Salir</g:link>)
			</sec:ifLoggedIn>
		</div>

        <nav:render group="tabs"/><br/>
		<nav:renderSubItems group="tabs"/>
    	<g:layoutBody/>    



    </body>
</html>
