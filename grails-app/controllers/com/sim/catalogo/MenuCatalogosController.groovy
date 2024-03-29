package com.sim.catalogo

class MenuCatalogosController {

    static navigation = [
        group: 'tabs', action: 'inicioCatalogos',title: 'Catálogos', order: 0,
		subItems: [
			[action:'irTipoAccesorio', order:20, title:'Tipo Accesorio'],
			[action:'irAccesorio', order:30, title:'Accesorio'],
			[action:'irBanco', order:40, title:'Banco'],
			[action:'irDescTelefono', order:50, title:'Desc. Teléfono'],
			[action:'irDiaFestivo', order:60, title:'Día Festivo'],
			[action:'irTipoDocumento', order:70, title:'Tipo Documento'],
			[action:'irReporte', order:80, title:'Reporte'],
			[action:'irDocumento', order:90, title:'Documento'],
			[action:'irEscolaridad', order:100, title:'Escolaridad'],
			[action:'irEtapaPrestamo', order:110, title:'Etapa Préstamo'],
			[action:'irFondeador', order:120, title:'Fondeador'],
			[action:'irLineaFondeo', order:130, title:'Línea de Fondeo'],
			[action:'irMetodoCalculo', order:140, title:'Método Cálculo'],
			[action:'irParentesco', order:150, title:'Parentesco'],
			[action:'irPerfil', order:160, title:'Perfil'],
			[action:'irPeriodicidad', order:170, title:'Periodicidad'],
			[action:'irPuesto', order:180, title:'Puesto'],
			[action:'irRechazoComite', order:190, title:'Rechazo Comité'],
			[action:'irTipoAsentamiento', order:200, title:'Tipo Asentamiento'],
			[action:'irTipoDomicilio', order:220, title:'Tipo Domicilio'],
			[action:'irTipoGarantia', order:230, title:'Tipo Garantia'],
			[action:'irTipoIdentificador', order:240, title:'Tipo Identificador'],
			[action:'irTipoNegocio', order:250, title:'Tipo Negocio'],
			[action:'irTipoPersona', order:260, title:'Tipo Persona'],
			[action:'irVerificacionReferencia', order:270, title:'Verif. Referencia'],
			[action:'irTasaReferencia', order:280, title:'Tasa Referencia'],
			[action:'irEstado', order:290, title:'Estado'],
			[action:'irCiudad', order:300, title:'Ciudad'],
			[action:'irDelegacionMunicipio', order:310, title:'Delegación o Mun.'],
			[action:'irAsentamiento', order:320, title:'Asentamiento'],
			[action:'irFormaEntrega', order:330, title:'Forma Entrega'],
			[action:'irGiro', order:340, title:'Giro Negocio'],
			[action:'irUbicacionNegocio', order:350, title:'Ubicación Negocio'],
			
			[action:'inicioPruebas', order:1000, title:'Pruebas']
		]
    ]

    def inicioCatalogos = { }
	
	def inicioPruebas = {}

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
	
	def irTipoAsentamiento ={
		redirect (controller:'simCatTipoAsentamiento', action:'list')
	}
	
	def irTipoDomicilio ={
		redirect (controller:'simCatTipoDomicilio', action:'list')
	}
	
	def irTipoGarantia ={
		redirect (controller:'simCatTipoGarantia', action:'list')
	}
	
	def irTipoIdentificador ={
		redirect (controller:'simCatTipoIdentificador', action:'list')
	}
	
	def irTipoNegocio ={
		redirect (controller:'simCatTipoNegocio', action:'list')
	}

	def irTipoPersona ={
		redirect (controller:'simCatTipoPersona', action:'list')
	}
	
	def irVerificacionReferencia ={
		redirect (controller:'simCatVerificacionReferencia', action:'list')
	}
	
	def irTasaReferencia ={
		redirect (controller:'simCatTasaReferencia', action:'list')
	}
	
	def irTasaPapel ={
		redirect (controller:'simCatTasaPapel', action:'list')
	}
	def irEstado ={
		redirect (controller:'rsGralEstado', action:'list')
	}
	def irCiudad ={
		redirect (controller:'rsGralCiudad', action:'list')
	}
	def irDelegacionMunicipio ={
		redirect (controller:'rsGralDelegacionMunicipio', action:'list')
	}
	def irAsentamiento ={
		redirect (controller:'rsGralAsentamiento', action:'list')
	}
	def irFormaEntrega ={
		redirect (controller:'simCatFormaEntrega', action:'list')
	}
	def irGiro ={
		redirect (controller:'simCatGiro', action:'list')
	}
	def irUbicacionNegocio={
		redirect (controller:'simCatUbicacionNegocio', action:'list')
	}
	
}
