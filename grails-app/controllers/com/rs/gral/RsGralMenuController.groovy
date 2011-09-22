package com.rs.gral

class RsGralMenuController {

    static navigation = [
        group: 'tabs', action: 'inicioEntidades',title: 'Empresa', order: 5,
		subItems: [
			[action:'irGrupoEmpresa', order:0, title:"Grupo Empresa"],
			[action:'irEmpresa', order:10, title:'Empresa'],
			[action:'irPersonas', order:20, title:'Personas'],
			[action:'irEmpleados', order:30, title:'Empleados'],
			[action:'irClientes', order:40, title:'Clientes'],
			[action:'irGaranteDespositario', order:50, title:'Garante Depositario'],
			[action:'irRegional', order:60, title:"Regional"],
			[action:'irSucursal', order:70, title:'Sucursal'],
			[action:'irDomicilios', order:80, title:'Domicilios'],
			[action:'irTelefonos', order:90, title:'Telefonos']
        ]
	]
	
	def inicioEntidades = { }
	
	def irGrupoEmpresa = {
		redirect (controller:'rsConfGpoEmpresa', action:'list')
	}

	def irEmpresa = {
		redirect (controller:'rsConfEmpresa', action:'list')
	}
	
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
