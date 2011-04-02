<html>
    <head>
        <title>Bienvenidos</title>
        <meta name="layout" content="main" />
    </head>
    <body>
		<form method="POST" action="${resource(file: 'j_spring_security_check')}">
		  <table>
			<tr>
			  <td>Usuario:</td><td><g:textField name="j_username"/></td>
			</tr>
			<tr>
			  <td>Contraseña:</td><td><input name="j_password" type="password"/></td>
			</tr>
			<tr>
			  <td colspan="2"><g:submitButton name="login" value="Login"/></td>
			</tr>
			<tr>
			  <td colspan="2">Intenta Usuario:"mike", Contraseña:"1234"</td>
			</tr>
		  </table>
		</form>
    </body>
</html>
