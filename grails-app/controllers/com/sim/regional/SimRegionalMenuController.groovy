package com.sim.regional

class SimRegionalMenuController {

    static navigation = [
        group: 'tabs', action: 'inicioRegional',title: 'Entidades', order: 50,
		subItems: [
			[action:'irRegional', order:0, title:"Regional"],
			[action:'irSucursal', order:10, title:'Sucursal'],
			[action:'irTelefonos', order:20, title:'Telefonos'],
			[action:'irDomicilios', order:30, title:'Domicilios'],
			[action:'irPersonas', order:40, title:'Personas'],
			[action:'irEmpleados', order:50, title:'Empleados'],
			[action:'irClientes', order:60, title:'Clientes']
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
	def irPersonas ={
		redirect (controller:'rsPersona', action:'list')
	}
	def irEmpleados ={
		redirect (controller:'rsEmpleado', action:'list')
	}
	def irClientes ={
		redirect (controller:'rsCliente', action:'list')
	}

}
