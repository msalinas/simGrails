<html>
	<head>
		<title>SIM</title>
		<meta name="layout" content="main" />
		<g:javascript library="prototype" />
	</head>
	<body>
	
		<form>
			<g:select optionKey="id" optionValue="name" name="country.name"
				id="country.name" from="${com.sim.prueba.Country.list()}"
				onchange="${remoteFunction(
			            controller:'country', 
			            action:'ajaxGetCities', 
			            params:'\'id=\' + escape(this.value)', 
			            onComplete:'updateCity(e)')}"></g:select>
			<g:select name="city" id="city"></g:select>
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
						opt.text = city.name 
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
			var zselect = document.getElementById('country.name') 
			var zopt = zselect.options[zselect.selectedIndex]
			${remoteFunction(controller:"country", action:"ajaxGetCities", params:"'id=' + zopt.value", onComplete:"updateCity(e)")}
		</g:javascript>	
	</body>
</html>


