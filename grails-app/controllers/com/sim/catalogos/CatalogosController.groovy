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
			[action:'irReporte', order:70, title:'Reporte'],
			[action:'irDocumento', order:70, title:'Documento']
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
		redirect (controller:'simTipoDocumentacion', action:'list')
	}

	def irReporte ={ 
		redirect (controller:'simCatReporte', action:'list')
	}

	def irDocumento ={ 
		redirect (controller:'simCatDocumento', action:'list')
	}


}
