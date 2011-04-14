package com.sim.regional

class SimRegionalMenuController {

    static navigation = [
        group: 'tabs', action: 'inicioRegional',title: 'Regional', order: 50,
		subItems: [
			[action:'irRegional', order:0, title:"Regional"],
			[action:'irSucursal', order:10, title:'Sucursal'],
			[action:'irTelefonos', order:20, title:'Telefonos']
        ]
	]
	
	def inicioRegional = { }
	
	def irRegional = {
		redirect (controller:'simRegional', action:'list')
	}
	
	def irSucursal = {
		redirect (controller:'simSucursal', action:'list')
	}
	def irTelefonos ={
		redirect (controller:'rsGralTelefono', action:'list')
	}
}
