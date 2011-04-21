<html>
	<head>
		<title>Cambiar Select</title>
		<meta name="layout" content="main" />
		<g:javascript library="prototype" />
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
			<g:emoticon happy="true">Mike</g:emoticon>
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
		
		<g:javascript>
			
			// This is called when the page loads to initialize Estados
			var zselectEstado = document.getElementById('rsGralEstado.nombreEstado')
			var zoptEstado = zselectEstado.options[zselectEstado.selectedIndex]
			${remoteFunction(controller:'rsGralEstado', action:'ajaxGetCiudades', params:"'id=' + zoptEstado.value", onComplete:'updateCiudad(e)')}
		
			function updateCiudad(e) {
				// The response comes back as a bunch-o-JSON
				var ciudades = eval('(' + e.responseText + ')') // evaluate JSON
			
			
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
							${remoteFunction(controller:'rsGralCiudad', action:'ajaxGetDelegacionMunicipio', params:"'id=' + ciudad.id", onComplete:'updateDelegacionMunicipio(e)')}
						}
					}
				}
			}
	
			function updateDelegacionMunicipio(e) {
				// The response comes back as a bunch-o-JSON
				var delegacionesMunicipios = eval('(' + e.responseText + ')') // evaluate JSON

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
						// OBTIENE LAS DELEGACIONES MUNICIPIOS DE LA PRIMERA CIUDAD QUE SE OBTIENE
						if (i==0){
							${remoteFunction(controller:'rsGralDelegacionMunicipio', action:'ajaxGetAsentamiento', params:"'id=' + delegacionMunicipio.id", onComplete:'updateAsentamiento(e)')}
						}
					}
				}
			}
			
			function updateAsentamiento(e) {
				// The response comes back as a bunch-o-JSON
				var asentamientos = eval('(' + e.responseText + ')') // evaluate JSON

				if (asentamientos) {
					var rselect = document.getElementById('asentamiento')
					var codigoPostal = document.getElementById('codigoPostal')
			
					// Clear all previous options
					 var l = rselect.length
			
					while (l > 0) {
						l--
						rselect.remove(l)
					}
			
					// Rebuild the select
					for (var i=0; i < asentamientos.length; i++) {
						var asentamiento = asentamientos[i]
						var opt = document.createElement('option');
						opt.text = asentamiento.nombreAsentamiento
						opt.value = asentamiento.id
						try {
							rselect.add(opt, null) // standards compliant; doesn't work in IE
						}
						catch(ex) {
							rselect.add(opt) // IE only
						}
						//ASIGNA EL CODIGO POSTAL DEL PRIMER ASENTAMIENTO OBTENIDO
						if (i==0){
							codigoPostal.value = asentamiento.codigoPostal
						}
						 
					}
				}
			}
			
			function updateCodigoPostal(e) {
				// The response comes back as a String
				var cp = e.responseText //String
				var codigoPostal = document.getElementById('codigoPostal')

				if (codigoPostal) {
					codigoPostal.value = cp
				}
			}
			
			//LAS SIGUIENTES FUNCIONES ACTUALIZAN LOS COMBOS DE ESTADO, CIUDAD, DELEGACION/MUNICIPIO
			//Y ASENTAMIENTO A PARTIR DEL CODIGO POSTAL
			function updateCombos(e) {
				var valores = eval('(' + e.responseText + ')') // evaluate JSON
				// OBTIENE EL ESTADO AL QUE PERTENECE EL CODIGO POSTAL
				var idEstado = valores[0];
				
				if (idEstado > 0) {
					
					var idCiudad = valores[1];
					var idDelegacionMunicipio = valores[2];
					var idAsentamiento = valores[3];
					
					var rselect = document.getElementById('rsGralEstado.nombreEstado')
					//ASIGNA AL COMBO DE ESTADO EL ESTADO SELECCIONADO A PARTIR DEL CODIGO POSTAL
					rselect.value = idEstado;
					
					${remoteFunction(controller:'rsGralEstado', action:'ajaxGetCiudades', params:"'id=' + idEstado", onComplete:'updateComboCiudad(e,idCiudad,idDelegacionMunicipio,idAsentamiento)')}
																							  
				}else{
					
					// LIMPIA EL COMBO DE CIUDADES
					var ciudad = document.getElementById('ciudad')
					// Clear all previous options
					 var l = ciudad.length
					while (l > 0) {
						l--
						ciudad.remove(l)
					}
					
					// LIMPIA EL COMBO DE DELEGACION/MUNICIPIO
					var delegacionMunicipio = document.getElementById('delegacionMunicipio')
					// Clear all previous options
					 var l = delegacionMunicipio.length
					while (l > 0) {
						l--
						delegacionMunicipio.remove(l)
					}
					
					// LIMPIA EL COMBO DE ASENTAMIENTO
					var asentamiento = document.getElementById('asentamiento')
					// Clear all previous options
					 var l = asentamiento.length
					while (l > 0) {
						l--
						asentamiento.remove(l)
					}
					
												
				}
			}
			
		//ACTUALIZA LAS CIUDADES QUE PERTENECEN AL ESTADO DEL CODIGO POSTAL SELECCIONADO
		function updateComboCiudad(e,idCiudad,idDelegacionMunicipio,idAsentamiento) {
				// The response comes back as a bunch-o-JSON
				var ciudades = eval('(' + e.responseText + ')') // evaluate JSON
			
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
					}
					//ASIGNA AL COMBO DE CIUDADES LA CIUDAD QUE PERTENECE AL CODIGO POSTAL
					rselect.value = idCiudad;
					${remoteFunction(controller:'rsGralCiudad', action:'ajaxGetDelegacionMunicipio', params:"'id=' + idCiudad", onComplete:'updateComboDelegacionMunicipio(e,idDelegacionMunicipio,idAsentamiento)')}
				}
			}

			//ACTUALIZA LAS DELEGACIONES/MUNICIPIOS QUE PERTENECEN A LA CIUDAD DEL CODIGO POSTAL SELECCIONADO
			function updateComboDelegacionMunicipio(e,idDelegacionMunicipio,idAsentamiento) {
				// The response comes back as a bunch-o-JSON
				var delegacionesMunicipios = eval('(' + e.responseText + ')') // evaluate JSON

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
					//ASIGNA AL COMBO DE DELEGACIONES/MUNICIPIOS LA DELEGACION/MUNICIPIO QUE PERTENECE AL CODIGO POSTAL
					rselect.value = idDelegacionMunicipio;
					${remoteFunction(controller:'rsGralDelegacionMunicipio', action:'ajaxGetAsentamiento', params:"'id=' + idDelegacionMunicipio", onComplete:'updateComboAsentamiento(e,idAsentamiento)')}
				}
			}
			
			//ACTUALIZA LOS ASENTAMIENTOS QUE PERTENECEN A LA DELEGACION/MUNICIPIO DEL CODIGO POSTAL SELECCIONADO
			function updateComboAsentamiento(e,idAsentamiento) {
				// The response comes back as a bunch-o-JSON
				var asentamientos = eval('(' + e.responseText + ')') // evaluate JSON

				if (asentamientos) {
					var rselect = document.getElementById('asentamiento')
					var codigoPostal = document.getElementById('codigoPostal')
			
					// Clear all previous options
					 var l = rselect.length
			
					while (l > 0) {
						l--
						rselect.remove(l)
					}
			
					// Rebuild the select
					for (var i=0; i < asentamientos.length; i++) {
						var asentamiento = asentamientos[i]
						var opt = document.createElement('option');
						opt.text = asentamiento.nombreAsentamiento
						opt.value = asentamiento.id
						try {
							rselect.add(opt, null) // standards compliant; doesn't work in IE
						}
						catch(ex) {
							rselect.add(opt) // IE only
						}
					}
					//ASIGNA AL COMBO DE ASENTAMIENTOS EL ASENTAMIENTO AL QUE PERTENECE EL CODIGO POSTAL
					rselect.value = idAsentamiento;
				}
			}
						
		</g:javascript>
	
	</body>
</html>