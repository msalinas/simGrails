package com.sim.catalogos

class CatalogosController {

    static navigation = [
        group: 'tabs', action: 'inicioCatalogos',title: 'Catalogos', order: 0,
		subItems: [
			[action:'irGrupoEmpresa', order:0, title:"Grupo Empresa"],
			[action:'irEmpresa', order:10, title:'Empresa'],
			[action:'irTipoAccesorio', order:20, title:'Tipo Accesorio'],
			[action:'irAccesorio', order:30, title:'Accesorio'],
			[action:'irBanco', order:40, title:'Banco'],
			[action:'irDescTelefono', order:50, title:'Descripcion Telefono'],
			[action:'irDiaFestivo', order:60, title:'Dia Festivo'],
			[action:'irTipoDocumento', order:70, title:'Tipo Documento'],
			[action:'irReporte', order:80, title:'Reporte'],
			[action:'irDocumento', order:90, title:'Documento'],
			[action:'irEscolaridad', order:100, title:'Escolaridad'],
			[action:'irEtapaPrestamo', order:110, title:'Etapa Prestamo'],
			[action:'irFondeador', order:120, title:'Fondeador'],
			[action:'irLineaFondeo', order:130, title:'Linea de Fondeo'],
			[action:'irMetodoCalculo', order:140, title:'Metodo Calculo'],
			[action:'irParentesco', order:150, title:'Parentesco'],
			[action:'irPerfil', order:160, title:'Perfil'],
			[action:'irPeriodicidad', order:170, title:'Periodicidad'],
			[action:'irPuesto', order:180, title:'Puesto'],
			[action:'irRechazoComite', order:190, title:'Rechazo Comite']
		]
    ]

    def inicioCatalogos = { }

    def irGrupoEmpresa = { 
		redirect (controller:'rsConfGpoEmpresa', action:'list')
	}

    def irEmpresa = { 
		redirect (controller:'rsConfEmpresa', action:'list')
	}

    def irTipoAccesorio = { 
		redirect (controller:'simCatTipoAccesorio', action:'list')
	}

    def irAccesorio = { 
		redirect (controller:'simCatAccesorio', action:'list')
	}

	def irBanco ={ 
		redirect (controller:'simCatBanco', action:'list')
	}

	def irDescTelefono ={ 
		redirect (controller:'simCatDescTelefono', action:'list')
	}

	def irDiaFestivo ={ 
		redirect (controller:'pfinDiaFestivo', action:'list')
	}

	def irTipoDocumento ={ 
		redirect (controller:'simCatTipoDocumento', action:'list')
	}

	def irReporte ={ 
		redirect (controller:'simCatReporte', action:'list')
	}

	def irDocumento ={ 
		redirect (controller:'simCatDocumento', action:'list')
	}

	def irEscolaridad ={
		redirect (controller:'simCatEscolaridad', action:'list')
	}
	
	def irEtapaPrestamo ={
		redirect (controller:'simCatEtapaPrestamo', action:'list')
	}

	def irFondeador ={
		redirect (controller:'simCatFondeador', action:'list')
	}
	
	def irLineaFondeo ={
		redirect (controller:'simCatLineaFondeo', action:'list')
	}
	
	def irMetodoCalculo ={
		redirect (controller:'simCatMetodoCalculo', action:'list')
	}
	def irParentesco ={
		redirect (controller:'simCatParentesco', action:'list')
	}
	
	def irPerfil ={
		redirect (controller:'simCatPerfil', action:'list')
	}
	
	def irPeriodicidad ={
		redirect (controller:'simCatPeriodicidad', action:'list')
	}
	def irPuesto ={
		redirect (controller:'simCatPuesto', action:'list')
	}
	def irRechazoComite ={
		redirect (controller:'simCatRechazoComite', action:'list')
	}
}
