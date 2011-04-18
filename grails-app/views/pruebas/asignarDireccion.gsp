<html>
	<head>
		<title>Asignar Direccion</title>
		<meta name="layout" content="main" />
		<g:javascript library="prototype" />
	</head>
	<body>

        <div class="nav">
            <span class="menuButton"><g:link class="home" controller="catalogos" action="inicioPruebas">Inicio Pruebas</g:link></span>
        </div>			
	
		<form>
			<g:select optionKey="id" optionValue="nombreEstado" name="rsGralEstado.nombreEstado"
				id="rsGralEstado.nombreEstado" from="${com.rs.gral.RsGralEstado.list()}"
				onchange="${remoteFunction(
			            controller:'rsGralEstado', 
			            action:'ajaxGetCiudades', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateCiudad(e)')}"></g:select>
			<br/>
			<g:select name="ciudad" id="ciudad" "
				onchange="${remoteFunction(
			            controller:'rsGralCiudad', 
			            action:'ajaxGetDelegacionMunicipio', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateDelegacionMunicipio(e)')}"></g:select>
			<br/>
			<g:select name="delegacionMunicipio" id="delegacionMunicipio"></g:select>
		</form>
			
		<g:javascript>
		
			// This is called when the page loads to initialize Estados
			var zselect = document.getElementById('rsGralEstado.nombreEstado')
			var zopt = zselect.options[zselect.selectedIndex]
			${remoteFunction(controller:"rsGralEstado", action:"ajaxGetCiudades", params:"'id=' + zopt.value", onComplete:"updateCiudad(e)")}
		
			function updateCiudad(e) {
				// The response comes back as a bunch-o-JSON 
				var ciudades = eval("(" + e.responseText + ")") // evaluate JSON
			
			
				if (ciudades) { 
					var rselect = document.getElementById('ciudad')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < ciudades.length; i++) { 
						var ciudad = ciudades[i] 
						var opt = document.createElement('option'); 
						opt.text = ciudad.nombreCiudad
						opt.value = ciudad.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						} 
						// OBTIENE LAS DELEGACIONES MUNICIPIOS DE LA PRIMERA CIUDAD QUE SE OBTIENE
						if (i==0){
							${remoteFunction(controller:"rsGralCiudad", action:"ajaxGetDelegacionMunicipio", params:"'id=' + ciudad.id", onComplete:"updateDelegacionMunicipio(e)")}
						}
					}
				}
			}
	
			function updateDelegacionMunicipio(e) {
				// The response comes back as a bunch-o-JSON 
				var delegacionesMunicipios = eval("(" + e.responseText + ")") // evaluate JSON

				if (delegacionesMunicipios) { 
					var rselect = document.getElementById('delegacionMunicipio')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < delegacionesMunicipios.length; i++) { 
						var delegacionMunicipio = delegacionesMunicipios[i] 
						var opt = document.createElement('option'); 
						opt.text = delegacionMunicipio.nombreDelegacionMunicipio
						opt.value = delegacionMunicipio.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						} 
					}
				}		
			}			
			
		</g:javascript>	
	</body>
</html>