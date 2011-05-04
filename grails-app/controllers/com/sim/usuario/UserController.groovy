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
	
	def edit = {
		def user = params.username ? lookupUserClass().findByUsername(params.username) : null
		if (!user) user = findById()
		if (!user) return
		
		//OBTIENE LA CLASE DE DOMINIO DE PERSONA
		Class<?> clasePersona = grailsApplication.getDomainClass("com.rs.gral.RsPersona").clazz
		def rsPersona = clasePersona.findByUsuario(user)

		return [persona: rsPersona, user: user]
	}
	
	def update = {
		def user = findById()
		if (!user) return
		if (!versionCheck('user.label', 'User', user, [user: user])) {
			return
		}

		def oldPassword = user.password
		user.properties = params
		if (params.password && !params.password.equals(oldPassword)) {
			String salt = saltSource instanceof NullSaltSource ? null : params.username
			user.password = springSecurityService.encodePassword(params.password, salt)
		}
		
		//OBTIENE LA CLASE DE DOMINIO DE PERSONA
		Class<?> clasePersona = grailsApplication.getDomainClass("com.rs.gral.RsPersona").clazz
		def rsPersona = clasePersona.findByUsuario(user)
		rsPersona.properties = params
		
		log.info rsPersona

		if (!rsPersona.validate()){
			user.validate()
			log.info "ACTUALIZACION INCORRECTA"
			render view: 'edit', model: [persona: rsPersona,  user: user]
			return
		}else{
			if (!user.save()) {
				render view: 'edit', model: [persona: rsPersona,  user: user]
				return
			}
			rsPersona.usuario = user
			rsPersona.save()
		}

		lookupUserRoleClass().removeAll user
		addRoles user
		userCache.removeUserFromCache user.username
		flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), rsPersona.apellidoPaterno +' '+ rsPersona.primerNombre])}"
		redirect action: edit, id: user.id
	}
		
}
