package com.sim.usuario

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource

class UserController extends grails.plugins.springsecurity.ui.UserController {

	static navigation = [
		group: 'tabs', action: 'search',title: 'Usuario', order: 40,
	]
	
	def save = {
		
		def user = lookupUserClass().newInstance(params)

		if (params.password) {
			String salt = saltSource instanceof NullSaltSource ? null : params.username
			user.password = springSecurityService.encodePassword(params.password, salt)
		}
		
		//OBTIENE LA CLASE DE DOMINIO DE PERSONA
		Class<?> clasePersona = grailsApplication.getDomainClass("com.rs.gral.RsPersona").clazz
		def rsPersona = clasePersona.newInstance(params)

		if (!rsPersona.validate()){
			user.validate()
			log.info "VALIDACION INCORRECTA"
			render view: 'create', model: [user: user, persona: rsPersona, authorityList: sortedRoles()]
			return
		}else{
			if (!user.save(flush: true)) {
				render view: 'create', model: [user: user, persona: rsPersona, authorityList: sortedRoles()]
				return
			}
			rsPersona.usuario = user
			
			rsPersona.save()
	
			addRoles(user)
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), rsPersona.apellidoPaterno +' '+ rsPersona.primerNombre])}"
			redirect action: edit, id: user.id
		}
	}
		
}
