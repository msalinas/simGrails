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
		
		estados.each{
			listaEstados = listaEstados + "<option value='${it.id}' >${it.nombreEstado}</option>"
		}
		
		def cadena = """
			<tr class='prop'>
				<td valign='top' class='name'><label>Estado:</label></td>
				<select name="rsGralEstado.nombreEstado" onchange="new Ajax.Request('/sim/rsGralEstado/ajaxGetCiudades',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCiudad(e)},parameters:'id=' + escape(this.value)});" id="rsGralEstado.nombreEstado" >
					${listaEstados}
				</select>
			</tr>
			<br />
			<tr class='prop'>
				<td valign='top' class='name'><label>Ciudad:</label></td>
				<select name="ciudad" onchange="new Ajax.Request('/sim/rsGralCiudad/ajaxGetDelegacionMunicipio',{asynchronous:true,evalScripts:true,onComplete:function(e){updateDelegacionMunicipio(e)},parameters:'id=' + escape(this.value)});" id="ciudad" >
				</select>
			</tr>
			<br />
			<tr class='prop'>
				<td valign='top' class='name'><label>Delegacion o
						Municipio:</label></td>
				<select name="delegacionMunicipio" onchange="new Ajax.Request('/sim/rsGralDelegacionMunicipio/ajaxGetAsentamiento',{asynchronous:true,evalScripts:true,onComplete:function(e){updateAsentamiento(e)},parameters:'id=' + escape(this.value)});" id="delegacionMunicipio" >
				</select>
			</tr>

			<br />
			<tr class='prop'>
				<td valign='top' class='name'><label>Colonia:</label></td>
				<select name="asentamiento" onchange="new Ajax.Request('/sim/rsGralAsentamiento/ajaxGetCodigoPostal',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCodigoPostal(e)},parameters:'id=' + escape(this.value)});" id="asentamiento" >
				</select>
			</tr>
			<br />
			<tr class='prop'>
				<td valign='top' class='name'><label>Codigo Postal:</label></td>
				<td valign='top'><input type="text" onKeyUp="new Ajax.Request('/sim/rsGralAsentamiento/ajaxGetCombos',{asynchronous:true,evalScripts:true,onComplete:function(e){updateCombos(e)},parameters:'cp=' + escape(this.value)});" name="codigoPostal" value="" id="codigoPostal" />
				</td>
			</tr>
			
			<script type="text/javascript">
				funcionIniciaDomicilio();
			</script>
		"""
		
		out << cadena
	}
}
