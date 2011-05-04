package com.rs.gral

import com.sim.regional.SimRegional
import com.sim.regional.SimSucursal

class RsGralDomicilioController {

	def index = { redirect(action: "list", params: params) }

	// the delete, save and update actions only accept POST requests
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def list = {
		params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
		[rsGralDomicilioInstanceList: RsGralDomicilio.list(params), rsGralDomicilioInstanceTotal: RsGralDomicilio.count()]
	}

	def create = {
		def rsGralDomicilioInstance = new RsGralDomicilio()
		rsGralDomicilioInstance.properties = params
		
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA REGIONAL
		if (params.simRegional){
			def simRegionalInstance = new SimRegional()
			simRegionalInstance = SimRegional.get(params.simRegional.id)
			rsGralDomicilioInstance.regional = simRegionalInstance
		}
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA SUCURSAL
		if (params.simSucursal){
			def simSucursalInstance = new SimSucursal()
			simSucursalInstance = SimSucursal.get(params.simSucursal.id)
			rsGralDomicilioInstance.sucursal = simSucursalInstance
		}
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA PERSONA
		if (params.rsPersona){
			def rsPersonaInstance = new RsPersona()
			rsPersonaInstance = RsPersona.get(params.rsPersona.id)
			rsGralDomicilioInstance.persona = rsPersonaInstance
		}

		return [rsGralDomicilioInstance: rsGralDomicilioInstance]
	}

	def save = {
		def rsGralDomicilioInstance = new RsGralDomicilio(params)
		if (!rsGralDomicilioInstance.hasErrors() && rsGralDomicilioInstance.save()) {
			flash.message = "rsGralDomicilio.created"
			flash.args = [rsGralDomicilioInstance.id]
			flash.defaultMessage = "RsGralDomicilio ${rsGralDomicilioInstance.id} created"
			redirect(action: "show", id: rsGralDomicilioInstance.id)
		}
		else {
			render(view: "create", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
		}
	}

	def show = {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = "rsGralDomicilio.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "RsGralDomicilio not found with id ${params.id}"
			redirect(action: "list")
		}
		else {
			return [rsGralDomicilioInstance: rsGralDomicilioInstance]
		}
	}

	def edit = {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = "rsGralDomicilio.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "RsGralDomicilio not found with id ${params.id}"
			redirect(action: "list")
		}
		else {
			return [rsGralDomicilioInstance: rsGralDomicilioInstance]
		}
	}

	def update = {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (rsGralDomicilioInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (rsGralDomicilioInstance.version > version) {

					rsGralDomicilioInstance.errors.rejectValue("version", "rsGralDomicilio.optimistic.locking.failure", "Another user has updated this RsGralDomicilio while you were editing")
					render(view: "edit", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
					return
				}
			}
			rsGralDomicilioInstance.properties = params
			if (!rsGralDomicilioInstance.hasErrors() && rsGralDomicilioInstance.save()) {
				flash.message = "rsGralDomicilio.updated"
				flash.args = [params.id]
				flash.defaultMessage = "RsGralDomicilio ${params.id} updated"
				redirect(action: "show", id: rsGralDomicilioInstance.id)
			}
			else {
				render(view: "edit", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
			}
		}
		else {
			flash.message = "rsGralDomicilio.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "RsGralDomicilio not found with id ${params.id}"
			redirect(action: "edit", id: params.id)
		}
	}

	def delete = {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (rsGralDomicilioInstance) {
			try {
				rsGralDomicilioInstance.delete()
				flash.message = "rsGralDomicilio.deleted"
				flash.args = [params.id]
				flash.defaultMessage = "RsGralDomicilio ${params.id} deleted"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "rsGralDomicilio.not.deleted"
				flash.args = [params.id]
				flash.defaultMessage = "RsGralDomicilio ${params.id} could not be deleted"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "rsGralDomicilio.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "RsGralDomicilio not found with id ${params.id}"
			redirect(action: "list")
		}
	}
}
