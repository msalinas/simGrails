<html>
	<head>
		<title>Cambiar Select</title>
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
            <span class="menuButton"><g:link class="list" controller="country" action="list"><g:message code="country.list" default="Country List" /></g:link></span>
            <span class="menuButton"><g:link class="list" controller="city" action="list"><g:message code="city.list" default="City List" /></g:link></span>
        </div>			
	
		<form>
			<g:select optionKey="id" optionValue="nameCountry" name="country.nameCountry"
				id="country.nameCountry" from="${com.sim.prueba.Country.list()}"
				onchange="${remoteFunction(
			            controller:'country', 
			            action:'ajaxGetCities', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateCity(e)')}"></g:select>
			<g:select name="city" id="city"></g:select>
			
			<br/>
			<br/>
			
			<g:backwards>Hola</g:backwards>
			<br/>
			<g:emoticon happy="true">mke</g:emoticon>
			<br/>
			<br/>
			<g:domicilio estados="${com.rs.gral.RsGralEstado.list()}"/>
		</form>
			
		<g:javascript>
			function updateCity(e) {
				// The response comes back as a bunch-o-JSON 
				var cities = eval("(" + e.responseText + ")") // evaluate JSON
			
			
				if (cities) { 
					var rselect = document.getElementById('city')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < cities.length; i++) { 
						var city = cities[i] 
						var opt = document.createElement('option'); 
						opt.text = city.nameCity
						opt.value = city.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						} 
					}
				}
			}
						
			// This is called when the page loads to initialize city 
			var zselect = document.getElementById('country.nameCountry') 
			var zopt = zselect.options[zselect.selectedIndex]
			${remoteFunction(controller:"country", action:"ajaxGetCities", params:"'id=' + zopt.value", onComplete:"updateCity(e)")}

		</g:javascript>	
	</body>
</html>