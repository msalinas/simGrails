package com.sim.regional

class SimRegionalMenuController {

    static navigation = [
        group: 'tabs', action: 'inicioRegional',title: 'Regional', order: 50,
		subItems: [
			[action:'irRegional', order:0, title:"Regional"],
			[action:'irSucursal', order:10, title:'Sucursal'],
			[action:'irTelefonos', order:20, title:'Telefonos'],
			[action:'irDomicilios', order:30, title:'Domicilios'],
			[action:'irEmpleado', order:30, title:'Empleados']
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
	def irDomicilios ={
		redirect (controller:'rsGralDomicilio', action:'list')
	}
	def irEmpleado ={
		redirect (controller:'rsEmpleado', action:'list')
	}
}
