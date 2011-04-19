package com.rs.gral

class RsGralAsentamientoController {

    def scaffold = true
	
	def ajaxGetCodigoPostal = {
		def asentamiento = RsGralAsentamiento.get(params.id)
		render asentamiento?.codigoPostal as String
	}
}
