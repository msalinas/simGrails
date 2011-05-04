package com.sim.usuario

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource

class UserController extends grails.plugins.springsecurity.ui.UserController {

	static navigation = [
		group: 'tabs', action: 'search',title: 'Usuario', order: 40,
	]
		
}
