function funcionIniciaDomicilio(){
	// This is called when the page loads to initialize Estados
	var zselect = document.getElementById('rsGralEstado.nombreEstado')
	var zopt = zselect.options[zselect.selectedIndex]
	new Ajax.Request('/sim/rsGralEstado/ajaxGetCiudades',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCiudad(e)},parameters:'id=' + zopt.value});
}	

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
				new Ajax.Request('/sim/rsGralCiudad/ajaxGetDelegacionMunicipio',{asynchronous:true,evalScripts:true,onComplete:function(e){updateDelegacionMunicipio(e)},parameters:'id=' + ciudad.id});
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
				new Ajax.Request('/sim/rsGralDelegacionMunicipio/ajaxGetAsentamiento',{asynchronous:true,evalScripts:true,onComplete:function(e){updateAsentamiento(e)},parameters:'id=' + delegacionMunicipio.id});
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
		
		new Ajax.Request('/sim/rsGralEstado/ajaxGetCiudades',{asynchronous:true,evalScripts:true,onComplete:function(e){updateComboCiudad(e,idCiudad,idDelegacionMunicipio,idAsentamiento)},parameters:'id=' + idEstado});
	                                                                  			
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
		new Ajax.Request('/sim/rsGralCiudad/ajaxGetDelegacionMunicipio',{asynchronous:true,evalScripts:true,onComplete:function(e){updateComboDelegacionMunicipio(e,idDelegacionMunicipio,idAsentamiento)},parameters:'id=' + idCiudad});
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
		new Ajax.Request('/sim/rsGralDelegacionMunicipio/ajaxGetAsentamiento',{asynchronous:true,evalScripts:true,onComplete:function(e){updateComboAsentamiento(e,idAsentamiento)},parameters:'id=' + idDelegacionMunicipio});
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
