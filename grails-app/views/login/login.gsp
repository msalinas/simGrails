<html>
    <head>
        <title>Usuario</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    
    	<sec:ifNotLoggedIn>
			<form method="POST" action="${resource(file: 'j_spring_security_check')}">
			  <table>
				<tr>
				  <td>Usuario:</td><td><g:textField name="j_username"/></td>
				</tr>
				<tr>
				  <td>Contrase&ntilde;a:</td><td><input name="j_password" type="password"/></td>
				</tr>
				<tr>
				  <td colspan="2"><g:submitButton name="login" value="Login"/></td>
				</tr>
				<tr>
				  <td colspan="2">Intenta Usuario:"admin", Contrase&ntilde;a:"4321"</td>
				</tr>
			  </table>
			</form>
		</sec:ifNotLoggedIn>
		
    </body>
</html>
