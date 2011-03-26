package com.sim.catalogos


class CatalogosController {

    static navigation = [
        group: 'tabs', action: 'inicioCatalogos',title: 'Catalogos', order: 0,
		subItems: [
			[action:'irGrupoEmpresa', order:0, title:"Grupo Empresa"],
			[action:'irEmpresa', order:10, title:'Empresa'],
			[action:'irTipoAccesorio', order:20, title:'Tipo Accesorio'],
			[action:'irAccesorio', order:30, title:'Accesorio'],
			[action:'irBanco', order:40, title:'Banco']
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

}
