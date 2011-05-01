package com.sim.usuario

class UsuarioController extends grails.plugins.springsecurity.ui.UserController {

	static navigation = [
		group: 'tabs', action: 'search',title: 'Usuario', order: 40,
	]
		
}
