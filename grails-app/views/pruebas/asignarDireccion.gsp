<html>
<head>
<title>Asignar Direccion</title>
<meta name="layout" content="main" />
<g:javascript library="prototype" />
</head>
<body>

	<div class="nav">
		<span class="menuButton"><g:link class="home"
				controller="catalogos" action="inicioPruebas">Inicio Pruebas</g:link>
		</span>
	</div>

	<form>
		<tr class="prop">
			<td valign="top" class="name"><label>Estado:</label></td>
			<g:select optionKey="id" optionValue="nombreEstado"
				name="rsGralEstado.nombreEstado" id="rsGralEstado.nombreEstado"
				from="${com.rs.gral.RsGralEstado.list()}"
				onchange="${remoteFunction(
			            controller:'rsGralEstado', 
			            action:'ajaxGetCiudades', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateCiudad(e)')}"></g:select>
		</tr>
		<br />
		<tr class="prop">
			<td valign="top" class="name"><label>Ciudad:</label></td>
			<g:select name="ciudad" id="ciudad"
				onchange="${remoteFunction(
			            controller:'rsGralCiudad', 
			            action:'ajaxGetDelegacionMunicipio', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateDelegacionMunicipio(e)')}"></g:select>
		</tr>
		<br />
		<tr class="prop">
			<td valign="top" class="name"><label>Delegacion o
					Municipio:</label></td>

			<g:select name="delegacionMunicipio" id="delegacionMunicipio"
				onchange="${remoteFunction(
			            controller:'rsGralDelegacionMunicipio', 
			            action:'ajaxGetAsentamiento', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateAsentamiento(e)')}"></g:select>
		</tr>
		<br />
		<tr class="prop">
			<td valign="top" class="name"><label>Colonia:</label></td>
			<g:select name="asentamiento" id="asentamiento"
				onchange="${remoteFunction(
			            controller:'rsGralAsentamiento', 
			            action:'ajaxGetCodigoPostal', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateCodigoPostal(e)')}"></g:select>
		</tr>
		<br />
		<tr class="prop">
			<td valign="top" class="name"><label>Codigo Postal:</label></td>
			<td valign="top"><g:textField name="codigoPostal" value=""
					onKeyUp="${remoteFunction(
			            controller:'rsGralAsentamiento', 
			            action:'ajaxGetCombos', 
			            params:'\'cp=\' + escape(this.value)', 
			            onComplete:'updateCombos(e)')}">

				</g:textField>
			</td>
		</tr>


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
						// OBTIENE LAS DELEGACIONES MUNICIPIOS DE LA PRIMERA CIUDAD QUE SE OBTIENE
						if (i==0){
							${remoteFunction(controller:"rsGralDelegacionMunicipio", action:"ajaxGetAsentamiento", params:"'id=' + delegacionMunicipio.id", onComplete:"updateAsentamiento(e)")}
						}						
					}
				}		
			}			
			
			function updateAsentamiento(e) {
				// The response comes back as a bunch-o-JSON 
				var asentamientos = eval("(" + e.responseText + ")") // evaluate JSON

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
			
			function updateCombos(e) {
				var valores = eval("(" + e.responseText + ")") // evaluate JSON
				
				if (valores.length == 4){

					for (var i=0; i < valores.length; i++) {
						var valor = valores[i]
									
						if (i==0){
							//alert ('Estado:'+valor)
							var rselect = document.getElementById('rsGralEstado.nombreEstado')
							rselect.value = valor;
							${remoteFunction(controller:"rsGralEstado", action:"ajaxGetCiudades", params:"'id=' + valor", onComplete:"updateCiudad(e)")}
						}
						if (i==1){
							//alert ('Ciudad:'+valor)
							var rselect = document.getElementById('ciudad')
							rselect.value = valor;
							${remoteFunction(controller:"rsGralCiudad", action:"ajaxGetDelegacionMunicipio", params:"'id=' + valor", onComplete:"updateDelegacionMunicipio(e)")}
						}
						if (i==2){
							//alert ('Delegacion Municipio:'+valor)
							var rselect = document.getElementById('delegacionMunicipio')
							rselect.value = valor;
							${remoteFunction(controller:"rsGralDelegacionMunicipio", action:"ajaxGetAsentamiento", params:"'id=' + valor", onComplete:"updateAsentamiento(e)")}
						}			
						if (i==3){
							//alert ('Asentamiento:'+valor)
							var rselect = document.getElementById('asentamiento')
							rselect.value = valor;
							${remoteFunction(controller:"rsGralAsentamiento", action:"ajaxGetCodigoPostal", params:"'id=' + valor", onComplete:"updateCodigoPostal(e)")}							
						}
					}
				}
				
			}			
						
		</g:javascript>
</body>
</html>