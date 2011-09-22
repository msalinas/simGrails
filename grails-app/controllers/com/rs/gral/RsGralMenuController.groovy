package com.rs.gral

class RsGralMenuController {

    static navigation = [
        group: 'tabs', action: 'inicioEntidades',title: 'Empresa', order: 5,
		subItems: [
			[action:'irPersonas', order:10, title:'Personas'],
			[action:'irEmpleados', order:20, title:'Empleados'],
			[action:'irClientes', order:30, title:'Clientes'],
			[action:'irGaranteDespositario', order:40, title:'Garante Depositario'],
			[action:'irRegional', order:50, title:"Regional"],
			[action:'irSucursal', order:60, title:'Sucursal'],
			[action:'irDomicilios', order:70, title:'Domicilios'],
			[action:'irTelefonos', order:80, title:'Telefonos']
        ]
	]
	
	def inicioEntidades = { }
	
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
	def irGaranteDespositario ={
		redirect (controller:'simClienteGaranteDepositario', action:'list')
	}

}
