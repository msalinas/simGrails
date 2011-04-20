package com.rs.gral

import grails.converters.*

class RsGralAsentamientoController {

	def scaffold = true

	def ajaxGetCodigoPostal = {
		def asentamiento = RsGralAsentamiento.get(params.id)
		render asentamiento?.codigoPostal as String
	}

	def ajaxGetCombos = {
		def codigoPostal = params.cp
		def tamanoCp  = params.cp.size()
		def a = JSON.parse("[]")
		if (tamanoCp == 5){
			println "Asigna codigo postal: ${codigoPostal}"
			def asentamiento = RsGralAsentamiento.findByCodigoPostal(codigoPostal)
			if (asentamiento){
				println asentamiento.id
				println asentamiento.delegacionMunicipio.id
				println asentamiento.delegacionMunicipio.ciudad.id
				println asentamiento.delegacionMunicipio.ciudad.estado.id
				a = JSON.parse("[ ${asentamiento.delegacionMunicipio.ciudad.estado.id}, ${asentamiento.delegacionMunicipio.ciudad.id}, ${asentamiento.delegacionMunicipio.id}, ${asentamiento.id}]")
			}else{
				println 'no existe cp'
			}
		}
		render a as JSON
	}
}
