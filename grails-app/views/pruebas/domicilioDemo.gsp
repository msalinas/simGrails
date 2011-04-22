<html>
	<head>
		<title>Demostracion Domicilio</title>
		<meta name="layout" content="main" />
		<g:javascript library="prototype" />
		<g:javascript src="domicilio.js" />
		
		<script language=javascript type=text/javascript>
		<!-- Script courtesy of http://www.web-source.net - Your Guide to Professional Web Site Design and Development
		//DESABILITAR TECLA ENTER	
		function stopRKey(evt) {
		   var evt = (evt) ? evt : ((event) ? event : null);
		   var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
		   if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
		}
		document.onkeypress = stopRKey;
		-->
		</script>				
		
	</head>
	<body>
       	<div class="nav">
            <span class="menuButton"><g:link class="home" controller="catalogos" action="inicioPruebas">Inicio Pruebas</g:link></span>
        </div>			
		<form>
			<g:domicilio estados="${com.rs.gral.RsGralEstado.list()}"/>
		</form>
	</body>
</html>