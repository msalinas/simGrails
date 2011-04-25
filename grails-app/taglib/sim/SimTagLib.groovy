package sim

class SimTagLib {

	def backwards = { attrs, body ->
		out << body().reverse()
	}

	def emoticon = { attrs, body ->
		out << body() << (attrs.happy == 'true' ? " :-)" : " :-(")
	}

	def domicilio = { attrs ->
		def listaEstados = ""
		
		def estados = attrs.estados

		// OBTIENE EL ASENTAMIENTO EN CASO DE YA EXISTIR EN LA CONSULTA DE DOMICILIO		
		def asentamiento = attrs.asentamiento
		
		estados.each{
			listaEstados = listaEstados + "<option value='${it.id}' >${it.nombreEstado}</option>"
		}
		
		def cadena = """
			<tr class='prop'>
				<td valign='top' class='name'><label>Estado:</label></td>
				<td valign="top" class="value ">
					<select name="rsGralEstado.nombreEstado" onchange="new Ajax.Request('/sim/rsGralEstado/ajaxGetCiudades',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCiudad(e)},parameters:'id=' + escape(this.value)});" id="rsGralEstado.nombreEstado" >
						${listaEstados}
					</select>
				 </td>	
			</tr>
			<tr class='prop'>
				<td valign='top' class='name'><label>Ciudad:</label></td>
				<td valign="top" class="value ">
					<select name="ciudad" onchange="new Ajax.Request('/sim/rsGralCiudad/ajaxGetDelegacionMunicipio',{asynchronous:true,evalScripts:true,onComplete:function(e){updateDelegacionMunicipio(e)},parameters:'id=' + escape(this.value)});" id="ciudad" >
					</select>
				</td>	
			</tr>
			<tr class='prop'>
				<td valign='top' class='name'><label>Delegacion o
						Municipio:</label></td>
				<td valign="top" class="value ">
					<select name="delegacionMunicipio" onchange="new Ajax.Request('/sim/rsGralDelegacionMunicipio/ajaxGetAsentamiento',{asynchronous:true,evalScripts:true,onComplete:function(e){updateAsentamiento(e)},parameters:'id=' + escape(this.value)});" id="delegacionMunicipio" >
					</select>
				</td>	
			</tr>
			<tr class='prop'>
				<td valign='top' class='name'><label>Colonia:</label></td>
				<td valign="top" class="value ">
					<select name="asentamiento" onchange="new Ajax.Request('/sim/rsGralAsentamiento/ajaxGetCodigoPostal',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCodigoPostal(e)},parameters:'id=' + escape(this.value)});" id="asentamiento" >
					</select>
				</td>	
			</tr>
			<tr class='prop'>
				<td valign='top' class='name'><label>Codigo Postal:</label></td>
				<td valign='top'><input type="text" onKeyUp="new Ajax.Request('/sim/rsGralAsentamiento/ajaxGetCombos',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCombos(e)},parameters:'cp=' + escape(this.value)});" name="rsGralAsentamiento.codigoPostal" value="" id="rsGralAsentamiento.codigoPostal" />
					<input type="hidden" name="rsGralAsentamiento.id" value="" id="rsGralAsentamiento.id" />
				</td>
			</tr>
			
			<script type="text/javascript">
				funcionIniciaDomicilio('${asentamiento?.codigoPostal}');
			</script>
		"""
		
		out << cadena
	}
}
